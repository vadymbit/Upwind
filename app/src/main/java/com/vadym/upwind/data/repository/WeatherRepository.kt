package com.vadym.upwind.data.repository

import com.vadym.upwind.data.model.api.WeatherForecast
import com.vadym.upwind.data.source.local.database.WeatherDAO
import com.vadym.upwind.data.source.remote.WeatherService
import com.vadym.upwind.model.WeatherListModel
import com.vadym.upwind.model.WeatherModel
import com.vadym.upwind.data.model.mapper.WeatherMapper
import kotlinx.coroutines.flow.*
import retrofit2.Response

class WeatherRepository(
    private val service: WeatherService,
    private val dao: WeatherDAO,
    settingsRepository: SettingsRepository
) {
    private val tempUnitType = settingsRepository.getTempUnits()
    private val windSpeedUnitType = settingsRepository.getWindSpeedUnits()
    private val timeFormat = settingsRepository.getTimeFormat()

    suspend fun loadWeatherByCity(
        lat: String,
        lon: String,
        cityId: Int,
        isDisplayed: Boolean
    ) {
        val response = getWeatherForecastByLocation("$lat, $lon")
        val countryCode = getCountryCodeByCity(lat, lon)

        if (response != null && response.isSuccessful) {
            response.body()?.let { forecast ->
                saveWeatherToDatabase(cityId, forecast, countryCode, isDisplayed)
            }
        }
    }

    suspend fun deleteCityWeather(cityId: Int) {
        deleteCityDatabase(cityId)
    }

    suspend fun updateWeatherListData() {
        dao.getCurrentWeatherList().forEach {
            loadWeatherByCity(
                lat = it.lat,
                lon = it.lon,
                cityId = it.cityId,
                isDisplayed = it.isDisplayed
            )
        }
    }

    suspend fun selectWeatherForDisplaying(weatherId: Int) {
        dao.unsetDisplayedWeather()
        dao.setWeatherForDisplay(weatherId)
    }

    fun getCityWeather(): Flow<WeatherModel> {
        return combine(
            dao.getWeather().filterNotNull(),
            tempUnitType,
            windSpeedUnitType,
            timeFormat
        ) { weather, tempUnitType, windSpeedUnitType, timeFormat ->
            WeatherMapper.weatherDBtoUIModel(
                weatherDB = weather,
                tempUnitType = tempUnitType,
                windSpeedUnitType = windSpeedUnitType,
                timeFormat = timeFormat
            )
        }
    }

    fun getWeatherList(): Flow<List<WeatherListModel>> {
        return combine(
            dao.getCurrentWeatherListFlow(),
            tempUnitType,
            windSpeedUnitType
        ) { weatherList, tempUnitType, windSpeedUnitType ->
            weatherList.map { weather ->
                WeatherMapper.currentWeatherDBtoWeatherList(
                    weatherDB = weather,
                    tempUnitType = tempUnitType,
                    windSpeedUnitType = windSpeedUnitType
                )
            }
        }
    }

    private suspend fun getWeatherForecastByLocation(location: String): Response<WeatherForecast>? {
        return runCatching {
            service.getWeatherForecast(location = location)
        }.getOrNull()
    }

    private suspend fun getCountryCodeByCity(lat: String, lon: String): String {
        return runCatching {
            service.getCountryCode(lat, lon).body() ?: ""
        }.getOrDefault("")
    }

    private suspend fun saveWeatherToDatabase(
        cityId: Int,
        weatherForecast: WeatherForecast,
        countryCode: String,
        isFirstCity: Boolean
    ) {
        deleteCityDatabase(cityId)
        dao.insertCurrentWeather(
            WeatherMapper.currentDTOtoDB(
                weatherForecast, countryCode, cityId, isFirstCity
            )
        )
        dao.insertDayForecast(
            weatherForecast.forecast.forecastDayList.map { dayForecast ->
                WeatherMapper.dailyForecastDTOtoDB(dayForecast, cityId)
            }
        )
        dao.insertHourForecast(
            weatherForecast.forecast.forecastDayList[0].hourForecast.map { hourForecast ->
                WeatherMapper.hourlyForecastDTOtoDB(hourForecast, cityId)
            }
        )
    }

    private suspend fun deleteCityDatabase(cityId: Int) {
        dao.deleteCurrentWeather(cityId)
        dao.deleteDailyWeather(cityId)
        dao.deleteHourlyWeather(cityId)
    }
}