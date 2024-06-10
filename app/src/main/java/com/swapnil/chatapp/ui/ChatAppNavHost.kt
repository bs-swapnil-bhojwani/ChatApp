package com.swapnil.chatapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swapnil.chatapp.feature.editProfile.EditProfileScreen
import com.swapnil.chatapp.feature.login.LoginScreen
import com.swapnil.chatapp.feature.splash.SplashScreen

@Composable
fun ChatAppNavHost(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.EditProfile.name){
        composable(Route.Splash.name){
            SplashScreen()
        }
        composable(Route.Login.name){
            LoginScreen()
        }
        composable(Route.EditProfile.name){
            EditProfileScreen()
        }
    }
}