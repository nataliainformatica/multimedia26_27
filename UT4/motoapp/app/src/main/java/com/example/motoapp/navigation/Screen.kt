package com.example.motoapp.navigation


sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Motorcycles : Screen("motorcycles")

    object Settings : Screen("settings")

    object Detail : Screen("detail/{motorcycleId}") {

        fun createRoute(id: Int): String {
            return "detail/$id"
        }
    }
}