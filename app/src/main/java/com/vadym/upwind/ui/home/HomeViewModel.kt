package com.vadym.upwind.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadym.upwind.data.repository.WeatherRepository
import com.vadym.upwind.model.CurrentWeatherModel
import com.vadym.upwind.model.WeatherModel
import com.vadym.upwind.utils.Const.UNIT_TYPE_KPH
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: WeatherRepository,
) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.updateWeatherListData()
        }
    }

    val weatherModel = repository.getCityWeather()
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(), WeatherModel(
                CurrentWeatherModel("", 0, "", 0, 0f, 0, UNIT_TYPE_KPH,"", ""),
                emptyList(),
                emptyList()
            )
        )
}
