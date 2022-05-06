package com.vadym.upwind.model

data class CurrentWeatherModel(
    val city: String,
    val temperature: Int,
    val condition: String,
    val pop: Int,
    val pressure: Float,
    val windSpeed: Int,
    val windSpeedUnitType: Int,
    val sunrise: String,
    val sunset: String
)