package com.vadym.upwind.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector
) {
    object WeatherHome : BottomBarScreen(
        route = "weather",
        icon = Icons.Outlined.Home
    )
    object WeatherList : BottomBarScreen(
        route = "weatherlist",
        icon = Icons.Outlined.List
    )
    object Settings : BottomBarScreen(
        route = "settings",
        icon = Icons.Outlined.Settings
    )
}