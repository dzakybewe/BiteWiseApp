package com.bewe.bitewiseapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bewe.bitewiseapp.ui.navigation.Screen
import com.bewe.bitewiseapp.ui.screens.detail.DetailScreen
import com.bewe.bitewiseapp.ui.screens.home.HomeScreen

@Composable
fun BiteWiseApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            /* Declare the routes for each screen */
            /* Home Screen */
            composable(Screen.Home.route) {
                HomeScreen()
            }

            /* Detail Screen */
            composable(Screen.Detail.route) {
                DetailScreen()
            }

        }
    }
}