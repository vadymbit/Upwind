package com.vadym.upwind.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vadym.upwind.utils.Const.TEMP_UNIT
import com.vadym.upwind.utils.Const.USER_LATITUDE
import com.vadym.upwind.utils.Const.USER_LONGITUDE
import com.vadym.upwind.utils.Const.WIND_SPEED_UNIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefsDataStore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SETTINGS")

    suspend fun setTempUnits(unitType: Int) {
        context.dataStore.edit { prefs ->
            prefs[TEMP_UNIT] = unitType
        }
    }

    fun getTempUnits() = context.dataStore.data.map { prefs ->
        prefs[TEMP_UNIT] ?: 0
    }

    suspend fun setWindSpeedUnits(unitType: Int) {
        context.dataStore.edit { prefs ->
            prefs[WIND_SPEED_UNIT] = unitType
        }
    }

    fun getWindSpeedUnits() = context.dataStore.data.map { prefs ->
        prefs[WIND_SPEED_UNIT] ?: 0
    }

    suspend fun setUserLocation(lat: Double, lon: Double) {
        context.dataStore.edit { prefs ->
            prefs[USER_LATITUDE] = lat
            prefs[USER_LONGITUDE] = lon
        }
    }

    fun getLocation(): Flow<Pair<Double?, Double?>> {
        return context.dataStore.data.map {  prefs ->
            Pair(prefs[USER_LATITUDE], prefs[USER_LONGITUDE])
        }
    }
}