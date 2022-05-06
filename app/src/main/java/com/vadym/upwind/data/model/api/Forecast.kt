package com.vadym.upwind.data.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDayList(
    @Json(name = "forecastday")
    val forecastDayList: List<Forecast>
)

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "date_epoch")
    val date: Long,
    @Json(name = "day")
    val dayForecast: ForecastDay,
    val astro: Astro,
    @Json(name = "hour")
    val hourForecast: List<ForecastHour>
)
