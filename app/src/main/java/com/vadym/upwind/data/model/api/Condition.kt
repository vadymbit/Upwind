package com.vadym.upwind.data.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(
    val text: String,
    val code: Int
)
