package com.vadym.upwind.utils.resourcehelper

import com.vadym.upwind.R

object IconResourceHelper {
    fun getResourceByCode(
        code: Int,
        filled: Boolean = false
    ): Int {
        return when (code) {
            1000 -> if (filled) R.drawable.ic_clear_sky_fill else R.drawable.ic_clear_sky
            1003 -> if (filled) R.drawable.ic_few_clouds_fill else R.drawable.ic_few_clouds
            1006, 1009 -> if (filled) R.drawable.ic_clouds_fill else R.drawable.ic_clouds
            1030, 1135, 1147 -> R.drawable.ic_mist
            1063, 1072, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1240, 1243, 1246
            -> if (filled) R.drawable.ic_rain_fill else R.drawable.ic_rain
            1066, 1114, 1210, 1213, 1216, 1219, 1222, 1237, 1255, 1258, 1261, 1264 -> R.drawable.ic_snow
            1069, 1204, 1207, 1249, 1252 -> if (filled) R.drawable.ic_sleet_fill else R.drawable.ic_sleet
            1087, 1273, 1276, 1279, 1282 -> R.drawable.ic_thunderstorm
            else -> R.drawable.ic_clear_sky
        }
    }
}