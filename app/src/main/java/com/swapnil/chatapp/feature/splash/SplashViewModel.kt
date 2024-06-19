package com.swapnil.chatapp.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.swapnil.chatapp.data.LocalRepo
import com.swapnil.chatapp.ui.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val localRepo: LocalRepo) : ViewModel() {
    fun checkLoginStatus(navController: NavController) {
        viewModelScope.launch {
            if (localRepo.isLoggedIn()) {
                navController.navigate(Screen.Home.route)
            } else {
                navController.navigate(Screen.Login.route)
            }
        }
    }
}