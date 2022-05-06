package com.vadym.upwind.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
    val id: Int = -1,
    val name: String,
    val region: String,
    val country: String,
    val lat: String,
    val lon: String,
    @Json(name = "tz_id")
    val timeZone: String = ""
)
