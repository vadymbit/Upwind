package com.vadym.upwind.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.vadym.upwind.utils.Const.HOME_ROUTE
import com.vadym.upwind.utils.Const.SETTINGS_ROUTE
import com.vadym.upwind.utils.Const.WEATHER_LIST_ROUTE

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector
) {
    object WeatherHome : BottomBarScreen(
        route = HOME_ROUTE,
        icon = Icons.Outlined.Home
    )
    object WeatherList : BottomBarScreen(
        route = WEATHER_LIST_ROUTE,
        icon = Icons.Outlined.List
    )
    object Settings : BottomBarScreen(
        route = SETTINGS_ROUTE,
        icon = Icons.Outlined.Settings
    )
}