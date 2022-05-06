package com.vadym.upwind.utils.resourcehelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vadym.upwind.R
import com.vadym.upwind.utils.Const.UNIT_TYPE_CELSIUS
import com.vadym.upwind.utils.Const.UNIT_TYPE_KPH
import com.vadym.upwind.utils.Const.UNIT_TYPE_MIPH

@Composable
fun getWindSpeedByUnitType(
    windSpeedUnitType: Int,
    windSpeedValue: Int = 10
): String {
    return stringResource(
        id = when (windSpeedUnitType) {
            UNIT_TYPE_KPH -> R.string.wind_speed_kph
            UNIT_TYPE_MIPH -> R.string.wind_speed_miph
            else -> R.string.wind_speed_mps
        },
        windSpeedValue
    )
}

@Composable
fun getTemperatureUnitTypeById(
    tempUnitType: Int,
    tempValue: Int = 10
): String {
    return stringResource(
        id = when (tempUnitType) {
            UNIT_TYPE_CELSIUS -> R.string.temp_c
            else -> R.string.temp_f
        },
        tempValue
    )
}