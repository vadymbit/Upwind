package com.vadym.upwind.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadym.upwind.data.repository.LocationRepository
import com.vadym.upwind.data.repository.SettingsRepository
import com.vadym.upwind.utils.Const.HOUR_FORMAT_24
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    val tempUnit = settingsRepository.getTempUnits()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)
    val windSpeedUnit = settingsRepository.getWindSpeedUnits()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)
    val timeFormat = settingsRepository.getTimeFormat()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HOUR_FORMAT_24)
    val currentLocation = locationRepository.getCurrentCity()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")

    fun setTempUnit(unit: Int) {
        viewModelScope.launch {
            settingsRepository.setTempUnit(unit)
        }
    }

    fun setWindSpeedUnit(unit: Int) {
        viewModelScope.launch {
            settingsRepository.setWindSpeedUnits(unit)
        }
    }

    fun requestUserLocation(): Boolean {
        if (isLocationServiceEnabled()) {
            viewModelScope.launch {
                locationRepository.saveUserLocation()
            }
            return true
        }
        return false
    }

    fun setTimeFormat(timeFormat: Int) {
        viewModelScope.launch {
            if (timeFormat == HOUR_FORMAT_24){
                settingsRepository.setTimeFormat(is24HourFormat = true)
            } else {
                settingsRepository.setTimeFormat(is24HourFormat = false)
            }
        }
    }

    private fun isLocationServiceEnabled(): Boolean {
        return locationRepository.isLocationEnabled()
    }
}