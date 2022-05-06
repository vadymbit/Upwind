package com.vadym.upwind.utils

import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object Const {
    const val BASE_URL = "https://api.weatherapi.com/v1/"

    //const preferences for preferences data store
    val TEMP_UNIT = intPreferencesKey("TEMP_UNIT")
    val WIND_SPEED_UNIT = intPreferencesKey("WIND_SPEED_UNIT")
    val USER_LATITUDE = doublePreferencesKey("USER_LATITUDE")
    val USER_LONGITUDE = doublePreferencesKey("USER_LONGITUDE")

    //Const for weather unit types
    const val UNIT_TYPE_CELSIUS = 0
    const val UNIT_TYPE_FAHRENHEIT = 1
    const val UNIT_TYPE_KPH = 0
    const val UNIT_TYPE_MIPH = 1
    const val UNIT_TYPE_MPS = 2

    //Unit converter constants for Kilometers per hour initial value
    const val MIPH_MULTIPLIER = 1.609344
    const val MPS_MULTIPLIER = 5 / 18f
}