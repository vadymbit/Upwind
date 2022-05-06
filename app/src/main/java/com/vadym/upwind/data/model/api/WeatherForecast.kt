package com.vadym.upwind.data.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecast(
    val location: City,
    val current: CurrentWeather,
    val forecast: ForecastDayList
)
