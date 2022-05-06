package com.vadym.upwind.model

import com.vadym.upwind.data.model.api.City

data class SearchBarState(
    val citiesList: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val text: String = ""
)