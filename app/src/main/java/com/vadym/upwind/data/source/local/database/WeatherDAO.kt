package com.vadym.upwind.data.source.local.database

import androidx.room.*
import com.vadym.upwind.data.model.database.CurrentWeatherDB
import com.vadym.upwind.data.model.database.DayForecastDB
import com.vadym.upwind.data.model.database.HourForecastDB
import com.vadym.upwind.data.model.database.WeatherDB
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(weatherList: CurrentWeatherDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayForecast(weatherList: List<DayForecastDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourForecast(weatherList: List<HourForecastDB>)

    @Query("SELECT * FROM currentweatherdb WHERE isDisplayed = 1")
    @Transaction
    fun getWeather(): Flow<WeatherDB>

    @Query("SELECT * FROM currentweatherdb")
    fun getCurrentWeatherListFlow(): Flow<List<CurrentWeatherDB>>

    @Query("SELECT * FROM currentweatherdb")
    suspend fun getCurrentWeatherList(): List<CurrentWeatherDB>

    @Query("UPDATE currentweatherdb SET isDisplayed = 1 WHERE city_id = :weatherId")
    suspend fun setWeatherForDisplay(weatherId: Int)

    @Query("UPDATE currentweatherdb SET isDisplayed = 0 WHERE isDisplayed = 1")
    suspend fun unsetDisplayedWeather()

    @Query("DELETE FROM currentweatherdb WHERE city_id = :cityId")
    suspend fun deleteCurrentWeather(cityId: Int)

    @Query("DELETE FROM hourforecastdb WHERE city_id = :cityId")
    suspend fun deleteHourlyWeather(cityId: Int)

    @Query("DELETE FROM dayforecastdb WHERE city_id = :cityId")
    suspend fun deleteDailyWeather(cityId: Int)
}