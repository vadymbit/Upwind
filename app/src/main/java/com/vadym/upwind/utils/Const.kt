package com.vadym.upwind.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object Const {
    const val BASE_URL = "https://api.weatherapi.com/v1/"

    //Bottom bar screen routes
    const val HOME_ROUTE = "HOME SCREEN"
    const val WEATHER_LIST_ROUTE = "WEATHER LIST SCREEN"
    const val SETTINGS_ROUTE = "SETTINGS SCREEN"

    //const preferences for preferences data store
    const val DATASTORE_NAME = "SETTINGS"
    val TEMP_UNIT = intPreferencesKey("TEMP_UNIT")
    val WIND_SPEED_UNIT = intPreferencesKey("WIND_SPEED_UNIT")
    val USER_LATITUDE = doublePreferencesKey("USER_LATITUDE")
    val USER_LONGITUDE = doublePreferencesKey("USER_LONGITUDE")
    val TIME_FORMAT = booleanPreferencesKey("TIME_FORMAT")

    //Const for weather unit types
    const val UNIT_TYPE_CELSIUS = 0
    const val UNIT_TYPE_FAHRENHEIT = 1
    const val UNIT_TYPE_KPH = 0
    const val UNIT_TYPE_MIPH = 1
    const val UNIT_TYPE_MPS = 2

    //Constants for time format
    const val HOUR_FORMAT_24 = 0
    const val HOUR_FORMAT_12 = 1

    //Unit converter constants for Kilometers per hour initial value
    const val MIPH_MULTIPLIER = 1.609344
    const val MPS_MULTIPLIER = 5 / 18f
}