package com.swapnil.chatapp.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.swapnil.chatapp.data.LocalRepo
import com.swapnil.chatapp.data.remote.UserRepo
import com.swapnil.chatapp.ui.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepo: UserRepo, private val localRepo: LocalRepo) :
    ViewModel() {

    fun onLoggedIn(email: String, navController: NavController) {
        viewModelScope.launch {
            val user = userRepo.getUserWithEmail(email)
            Log.e("onLoggedIn","user is ${user.toString()}")
            if (user != null) {
                localRepo.onLoggedIn()
                navController.navigate(Screen.Home.route)
            } else {
                navController.navigate(Screen.EditProfile(email).route)
            }
        }
    }
}