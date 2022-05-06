package com.vadym.upwind.model

import androidx.annotation.DrawableRes

data class DailyForecastModel(
    val dayOfWeek: String,
    @DrawableRes val condition: Int,
    val tempDay: Int,
    val tempNight: Int
)
