package com.vadym.upwind.model

import androidx.annotation.DrawableRes

data class HourlyForecastModel(
    val time: String,
    @DrawableRes val condition: Int,
    val temp: Int
)
