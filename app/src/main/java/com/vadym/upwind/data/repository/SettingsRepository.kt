package com.vadym.upwind.data.repository

import com.vadym.upwind.data.source.local.PrefsDataStore
import com.vadym.upwind.utils.Const.HOUR_FORMAT_12
import com.vadym.upwind.utils.Const.HOUR_FORMAT_24
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val prefsDataStore: PrefsDataStore) {

    suspend fun setTempUnit(unitType: Int) {
        prefsDataStore.setTempUnits(unitType)
    }

    fun getTempUnits(): Flow<Int> = prefsDataStore.getTempUnits()


    suspend fun setWindSpeedUnits(unitType: Int) {
        prefsDataStore.setWindSpeedUnits(unitType)
    }

    fun getWindSpeedUnits() = prefsDataStore.getWindSpeedUnits()

    suspend fun setTimeFormat(is24HourFormat: Boolean) {
        prefsDataStore.setTimeFormat(is24HourFormat)
    }

    fun getTimeFormat() = prefsDataStore.getTimeFormat().map {
        if (it) HOUR_FORMAT_24 else HOUR_FORMAT_12
    }

}