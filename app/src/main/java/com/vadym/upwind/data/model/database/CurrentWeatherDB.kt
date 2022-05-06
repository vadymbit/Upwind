package com.vadym.upwind.data.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentWeatherDB(
    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val cityId: Int,
    val lat: String,
    val lon: String,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    @ColumnInfo(name = "county_code")
    val countryCode: String,
    @ColumnInfo(name = "city_time_zone")
    val cityTimeZone: String,
    @ColumnInfo(name = "temp_c")
    val tempC: Float,
    @ColumnInfo(name = "temp_f")
    val tempF: Float,
    val pop: Int,
    @ColumnInfo(name = "wind_speed_kph")
    val windSpeedKph: Float,
    @ColumnInfo(name = "wind_speed_mph")
    val windSpeedMph: Float,
    val pressure: Float,
    val sunrise: String,
    val sunset: String,
    val condition: String,
    @ColumnInfo(name = "condition_code")
    val conditionCode: Int,
    val isDisplayed: Boolean = false
)

