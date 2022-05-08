package com.vadym.upwind.data.model.mapper

import com.vadym.upwind.data.model.api.Forecast
import com.vadym.upwind.data.model.api.ForecastHour
import com.vadym.upwind.data.model.api.WeatherForecast
import com.vadym.upwind.data.model.database.CurrentWeatherDB
import com.vadym.upwind.data.model.database.DayForecastDB
import com.vadym.upwind.data.model.database.HourForecastDB
import com.vadym.upwind.data.model.database.WeatherDB
import com.vadym.upwind.model.*
import com.vadym.upwind.utils.TimeUtils
import com.vadym.upwind.utils.UnitTypeUtils
import com.vadym.upwind.utils.resourcehelper.IconResourceHelper

object WeatherMapper {
    fun currentDTOtoDB(
        it: WeatherForecast,
        countryCode: String,
        cityId: Int,
        isFirstCity: Boolean
    ) = CurrentWeatherDB(
        cityId = cityId,
        lat = it.location.lat,
        lon = it.location.lon,
        cityName = it.location.name,
        countryCode = countryCode,
        cityTimeZone = it.location.timeZone,
        tempC = it.current.tempC,
        tempF = it.current.tempF,
        pop = it.forecast.forecastDayList[0].dayForecast.chanceOfRain,
        windSpeedKph = it.current.windKph,
        windSpeedMph = it.current.windMph,
        pressure = it.current.pressure,
        sunrise = it.forecast.forecastDayList[0].astro.sunrise,
        sunset = it.forecast.forecastDayList[0].astro.sunset,
        condition = it.current.condition.text,
        conditionCode = it.current.condition.code,
        isDisplayed = isFirstCity
    )

    fun dailyForecastDTOtoDB(forecast: Forecast, cityId: Int) =
        DayForecastDB(
            date = forecast.date,
            cityId = cityId,
            maxTempC = forecast.dayForecast.maxTempC,
            maxTempF = forecast.dayForecast.maxTempF,
            minTempC = forecast.dayForecast.minTempC,
            minTempF = forecast.dayForecast.minTempF,
            conditionCode = forecast.dayForecast.condition.code
        )

    fun hourlyForecastDTOtoDB(hourForecast: ForecastHour, cityId: Int) =
        HourForecastDB(
            cityId = cityId,
            time = hourForecast.time,
            tempC = hourForecast.tempC,
            tempF = hourForecast.tempF,
            conditionCode = hourForecast.condition.code

        )

    fun currentWeatherDBtoWeatherList(
        weatherDB: CurrentWeatherDB,
        tempUnitType: Int,
        windSpeedUnitType: Int
    ) = weatherDB.run {
        WeatherListModel(
            cityId = cityId,
            temperature = UnitTypeUtils.setTempByUnitType(tempC, tempUnitType),
            city = cityName,
            country = countryCode,
            windSpeed = UnitTypeUtils.setWindSpeedByUnitType(windSpeedKph, windSpeedUnitType),
            windSpeedUnitType = windSpeedUnitType,
            pop = pop,
            condition = IconResourceHelper.getResourceByCode(conditionCode)
        )
    }

    fun weatherDBtoUIModel(
        weatherDB: WeatherDB,
        tempUnitType: Int,
        windSpeedUnitType: Int,
        timeFormat: Int
    ): WeatherModel {
        val currentWeather: CurrentWeatherModel = weatherDB.currentWeather.run {
            CurrentWeatherModel(
                city = cityName,
                temperature = UnitTypeUtils.setTempByUnitType(tempC, tempUnitType),
                condition = condition,
                pop = pop,
                pressure = pressure,
                windSpeed = UnitTypeUtils.setWindSpeedByUnitType(windSpeedKph, windSpeedUnitType),
                windSpeedUnitType = windSpeedUnitType,
                sunrise = TimeUtils.convertAstroTimeToRegional(sunrise, timeFormat),
                sunset = TimeUtils.convertAstroTimeToRegional(sunset, timeFormat)
            )
        }
        val dailyForecast = weatherDB.dailyForecast.map {
            DailyForecastModel(
                dayOfWeek = TimeUtils.getDayOfWeekForUnixTime(it.date),
                condition = IconResourceHelper.getResourceByCode(
                    code = it.conditionCode,
                    filled = true
                ),
                tempDay = UnitTypeUtils.setTempByUnitType(it.maxTempC, tempUnitType),
                tempNight = UnitTypeUtils.setTempByUnitType(it.minTempC, tempUnitType)
            )
        }
        val hourlyForecast = weatherDB.hourlyForecast.map {
            HourlyForecastModel(
                time = TimeUtils.convertUnixTimeToRegional(
                    it.time.toLong(),
                    timeFormat,
                    weatherDB.currentWeather.cityTimeZone
                ),
                condition = IconResourceHelper.getResourceByCode(
                    code = it.conditionCode,
                    filled = true
                ),
                temp = UnitTypeUtils.setTempByUnitType(it.tempC, tempUnitType)
            )
        }

        return WeatherModel(currentWeather, dailyForecast, hourlyForecast)
    }

}

