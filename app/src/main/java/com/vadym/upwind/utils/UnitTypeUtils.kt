package com.vadym.upwind.utils

import com.vadym.upwind.utils.Const.MIPH_MULTIPLIER
import com.vadym.upwind.utils.Const.MPS_MULTIPLIER
import com.vadym.upwind.utils.Const.UNIT_TYPE_CELSIUS
import com.vadym.upwind.utils.Const.UNIT_TYPE_KPH
import com.vadym.upwind.utils.Const.UNIT_TYPE_MIPH
import kotlin.math.roundToInt

object UnitTypeUtils {
    fun setTempByUnitType(celsiusValue: Float, unitType: Int): Int {
        return if (unitType == UNIT_TYPE_CELSIUS) {
            celsiusValue.roundToInt()
        } else {
            //Convert Celsius to Fahrenheit
            (celsiusValue * 1.8 + 32).roundToInt()
        }
    }

    fun setWindSpeedByUnitType(unit: Float, unitType: Int): Int {
        return when (unitType) {
            UNIT_TYPE_KPH -> unit.roundToInt()
            UNIT_TYPE_MIPH -> (unit / MIPH_MULTIPLIER).roundToInt()
            else -> (unit * MPS_MULTIPLIER).roundToInt()
        }
    }
}