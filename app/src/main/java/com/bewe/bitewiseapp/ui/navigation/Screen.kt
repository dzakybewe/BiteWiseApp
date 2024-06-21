package com.bewe.bitewiseapp.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")

    data object Detail: Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    data object History: Screen("history")
    data object Landing: Screen("landing")
    data object Preferences: Screen("preferences")
}