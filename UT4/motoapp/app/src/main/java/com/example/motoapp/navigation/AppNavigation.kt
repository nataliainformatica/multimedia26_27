package com.example.motoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motoapp.screens.HomeScreen

import com.example.motoapp.screens.MotorcyclesScreen
import com.example.motoapp.screens.MotorcycleDetailScreen
import com.example.motoapp.screens.SettingsScreen
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        // ruta inicial
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(
                onNavigateToMotorcycles = {
                    navController.navigate(
                        Screen.Motorcycles.route
                    )
                },
                onNavigateToSettings = {
                    navController.navigate(
                        Screen.Settings.route
                    )
                }
            )
        }

        composable(Screen.Motorcycles.route) {

            MotorcyclesScreen(
                onMotorcycleClick = { id ->
                    navController.navigate(
                        Screen.Detail.createRoute(id)
                    )
                }
            )
        }

        composable(
            route = Screen.Detail.route
        ) { backStackEntry ->

            val motorcycleId = backStackEntry
                .arguments
                ?.getString("motorcycleId")
                ?.toIntOrNull() ?: 0

            MotorcycleDetailScreen(
                motorcycleId = motorcycleId
            )
        }

        composable(Screen.Settings.route) {

            SettingsScreen()
        }
    }
}