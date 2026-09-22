package com.galindo.laboratorio.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object List : Screen("list")

    data object Detail : Screen("detail/{itemId}") {

        fun createRoute(itemId: Int): String {
            return "detail/$itemId"
        }
    }

    data object Profile : Screen("profile")
}