package com.vadym.upwind.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastHour(
    @Json(name = "time_epoch")
    val time: Int,
    @Json(name = "temp_c")
    val tempC: Float,
    @Json(name = "temp_f")
    val tempF: Float,
    val condition: Condition,
)
