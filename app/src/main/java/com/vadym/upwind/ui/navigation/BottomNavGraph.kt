package com.vadym.upwind.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vadym.upwind.ui.weatherlist.WeatherListScreen
import com.vadym.upwind.ui.home.HomeViewModel
import com.vadym.upwind.ui.home.WeatherScreen
import com.vadym.upwind.ui.settings.SettingsScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.WeatherHome.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.WeatherHome.route) {
            val homeViewModel: HomeViewModel = getViewModel()
            WeatherScreen(homeViewModel = homeViewModel)
        }
        composable(route = BottomBarScreen.WeatherList.route) {
            WeatherListScreen { navController.navigateUp() }
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}