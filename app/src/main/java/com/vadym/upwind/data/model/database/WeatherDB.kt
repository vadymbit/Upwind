package com.vadym.upwind.data.model.database

import androidx.room.Embedded
import androidx.room.Relation

data class WeatherDB(
    @Embedded val currentWeather: CurrentWeatherDB,
    @Relation(
        parentColumn = "city_id",
        entityColumn = "city_id"
    )
    val dailyForecast: List<DayForecastDB>,
    @Relation(
        parentColumn = "city_id",
        entityColumn = "city_id"
    )
    val hourlyForecast:  List<HourForecastDB>
)
