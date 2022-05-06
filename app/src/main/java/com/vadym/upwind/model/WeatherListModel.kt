package com.vadym.upwind.model

import androidx.annotation.DrawableRes

data class WeatherListModel(
    val cityId: Int,
    val temperature: Int,
    val city: String,
    val country: String,
    val windSpeed: Int,
    val windSpeedUnitType: Int,
    val pop: Int,
    @DrawableRes val condition: Int
)
