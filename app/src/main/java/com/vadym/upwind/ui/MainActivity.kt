package com.vadym.upwind.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vadym.upwind.ui.navigation.BottomBarScreen
import com.vadym.upwind.ui.navigation.BottomNavGraph
import com.vadym.upwind.ui.theme.UpwindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    UpwindTheme {
        val navController = rememberNavController()
        val systemUiController = rememberSystemUiController()
        val scaffoldState = rememberScaffoldState()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent
            )
        }
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) {
            BottomNavGraph(
                scaffoldState = scaffoldState,
                navController = navController,
                modifier = Modifier
                    .padding(it)
                    .statusBarsPadding()
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.WeatherHome,
        BottomBarScreen.WeatherList,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.navigationBarsPadding()
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.route
            )
        },
        selectedContentColor = MaterialTheme.colors.secondary.copy(0.7f),
        unselectedContentColor = Color.White.copy(0.35f),
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    )
}