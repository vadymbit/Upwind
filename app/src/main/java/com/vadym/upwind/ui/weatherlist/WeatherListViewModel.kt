package com.vadym.upwind.ui.weatherlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadym.upwind.data.repository.WeatherRepository
import com.vadym.upwind.data.model.api.City
import com.vadym.upwind.data.repository.LocationRepository
import com.vadym.upwind.model.SearchBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherListViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val cities = MutableStateFlow<List<City>>(emptyList())
    private val searchText = MutableStateFlow("")
    private val isLoading = MutableStateFlow(false)
    val weatherList = weatherRepository.getWeatherList()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val searchBarState = combine(cities, isLoading, searchText) { citiesList, isLoading, text ->
        SearchBarState(
            citiesList = citiesList,
            isLoading = isLoading,
            text = text
        )
    }

    fun displayWeatherOnHomeScreen(weatherId: Int) {
        viewModelScope.launch {
            weatherRepository.selectWeatherForDisplaying(weatherId)
        }
    }


    fun onQueryChange(query: String) {
        searchText.value = query
        findCities()
    }

    private fun findCities() {
        viewModelScope.launch {
            val list = locationRepository.getCities(searchText.value)
            cities.emit(list)
        }
    }

    fun getCityWeather(city: City) {
        viewModelScope.launch {
            weatherRepository.loadWeatherByCity(
                lat = city.lat,
                lon = city.lon,
                cityId = city.id,
                isDisplayed = weatherList.value.isEmpty()
            )
        }
    }

    fun deleteCityWeather(cityId: Int) {
        viewModelScope.launch {
            weatherRepository.deleteCityWeather(cityId)
        }
    }

    fun clearSearchField() {
        searchText.value = ""
        cities.value = emptyList()
    }

    override fun onCleared() {
        Log.d("ViewModel", "ViewModel Destroyed")
    }
}