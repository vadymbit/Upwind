package com.vadym.upwind.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDay(
    @Json(name = "maxtemp_c")
    val maxTempC: Float,
    @Json(name = "maxtemp_f")
    val maxTempF: Float,
    @Json(name = "mintemp_c")
    val minTempC: Float,
    @Json(name = "mintemp_f")
    val minTempF: Float,
    @Json(name = "maxwind_kph")
    val windKph: Float,
    @Json(name = "maxwind_mph")
    val windMph: Float,
    val condition: Condition,
    @Json(name = "daily_chance_of_rain")
    val chanceOfRain: Int,
    @Json(name = "daily_chance_of_snow")
    val chanceOfSnow: Int
)
