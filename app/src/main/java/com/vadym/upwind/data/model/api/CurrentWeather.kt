package com.vadym.upwind.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @Json(name = "last_updated_epoch")
    val updatedAt: Int,
    @Json(name = "temp_c")
    val tempC: Float,
    @Json(name = "temp_f")
    val tempF: Float,
    val condition: Condition,
    @Json(name = "wind_kph")
    val windKph: Float,
    @Json(name = "wind_mph")
    val windMph: Float,
    @Json(name = "pressure_mb")
    val pressure: Float

)