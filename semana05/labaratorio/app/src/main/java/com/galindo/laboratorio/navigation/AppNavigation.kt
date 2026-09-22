package com.galindo.laboratorio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.galindo.laboratorio.screens.DetailScreen
import com.galindo.laboratorio.screens.HomeScreen
import com.galindo.laboratorio.screens.ListScreen
import com.galindo.laboratorio.screens.ProfileScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = Screen.List.route
        ) {
            ListScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0

            DetailScreen(
                itemId = itemId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}