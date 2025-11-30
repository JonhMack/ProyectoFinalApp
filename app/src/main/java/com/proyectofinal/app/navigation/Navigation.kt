package com.proyectofinal.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.proyectofinal.app.ui.login.LoginScreen
import com.proyectofinal.app.ui.register.RegisterScreen
import com.proyectofinal.app.ui.profile.ProfileScreen

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Register : Screens("register")
    object Profile : Screens("profile/{userId}") {
        fun createRoute(id: Long) = "profile/$id"
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Login.route) {

        composable(Screens.Login.route) {
            LoginScreen(navController)
        }

        composable(Screens.Register.route) {
            RegisterScreen(navController)
        }

        composable(
            route = Screens.Profile.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
            ProfileScreen(userId = userId, navController = navController)
        }
    }
}
