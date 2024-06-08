package com.bewe.bitewiseapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bewe.bitewiseapp.pref.SharedPreferencesHelper
import com.bewe.bitewiseapp.ui.navigation.NavigationItem
import com.bewe.bitewiseapp.ui.navigation.Screen
import com.bewe.bitewiseapp.ui.screens.detail.DetailScreen
import com.bewe.bitewiseapp.ui.screens.history.HistoryScreen
import com.bewe.bitewiseapp.ui.screens.home.HomeScreen
import com.bewe.bitewiseapp.ui.screens.landing.LandingScreen
import com.bewe.bitewiseapp.ui.screens.preferences.PreferencesScreen

@Composable
fun BiteWiseApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val userId = remember { SharedPreferencesHelper.getUserId(context) }

    val navBackstackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackstackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (
                currentRoute != Screen.Landing.route &&
                currentRoute != Screen.Preferences.route &&
                currentRoute != Screen.Detail.route
            ) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Landing.route, // Logika If else pernah ngisi kuesioner atau ngga, kalo udah ke home, kalo ngga ke landing
            modifier = Modifier.padding(innerPadding)
        ) {

            /* Declare the routes for each screen */
            /* Home Screen */
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.Detail.route)
                    }
                )
            }

            /* Detail Screen */
            composable(Screen.Detail.route) {
                DetailScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }

            /* Detail Screen */
            composable(Screen.History.route) {
                HistoryScreen()
            }

            /* Landing Screen */
            composable(Screen.Landing.route) {
                LandingScreen(
                    navigateToPreferences = {
                        navController.navigate(Screen.Preferences.route)
                    }
                )
            }

            /* Preferences Screen */
            composable(Screen.Preferences.route) {
                PreferencesScreen(
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }

        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "History",
                icon = Icons.Default.History,
                screen = Screen.History
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}