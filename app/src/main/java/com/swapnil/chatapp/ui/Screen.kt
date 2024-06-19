package com.swapnil.chatapp.ui

sealed class Screen(val route: String) {
    data object Splash : Screen(route = "Splash")
    data object Login : Screen(route = "Login")
    data object Home : Screen(route = "Home")
    class EditProfile(email: String) : Screen(route = "EditProfile?email=$email") {
        companion object {
            fun getNavArgsFormat() = "EditProfile?email={email}"
        }
    }
}