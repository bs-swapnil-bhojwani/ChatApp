package com.swapnil.chatapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.swapnil.chatapp.feature.HomeScreen
import com.swapnil.chatapp.feature.editProfile.EditProfileScreen
import com.swapnil.chatapp.feature.login.LoginScreen
import com.swapnil.chatapp.feature.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(koinViewModel(),navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController, koinViewModel())
        }
        composable(Screen.EditProfile.getNavArgsFormat(), arguments = listOf(navArgument("email") {
            type = NavType.StringType
        })) {
            val email = it.arguments?.getString("email") ?: error("Email argument not passed")
            EditProfileScreen(navController = navController,viewModel = koinViewModel(), email = email)
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}