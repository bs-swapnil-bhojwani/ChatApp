package com.swapnil.chatapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.swapnil.chatapp.feature.editProfile.EditProfileScreen
import com.swapnil.chatapp.feature.login.LoginScreen
import com.swapnil.chatapp.feature.splash.SplashScreen

@Composable
fun ChatAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Login.name) {
        composable(Route.Splash.name) {
            SplashScreen()
        }
        composable(Route.Login.name) {
            LoginScreen(navController)
        }
        composable("EditProfile?email={email}", arguments = listOf(navArgument("email") {
            type = NavType.StringType
        })) {
            val email = it.arguments?.getString("email") ?: error("Email argument not passed")
            EditProfileScreen(email)
        }
    }
}