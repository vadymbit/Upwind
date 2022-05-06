package com.vadym.upwind.data.repository

import com.vadym.upwind.data.source.local.PrefsDataStore
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val prefsDataStore: PrefsDataStore) {

    suspend fun setTempUnit(unitType: Int) {
        prefsDataStore.setTempUnits(unitType)
    }

    fun getTempUnits(): Flow<Int> = prefsDataStore.getTempUnits()


    suspend fun setWindSpeedUnits(unitType: Int) {
        prefsDataStore.setWindSpeedUnits(unitType)
    }

    fun getWindSpeedUnits() = prefsDataStore.getWindSpeedUnits()

}