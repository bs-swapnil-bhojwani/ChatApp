package com.swapnil.chatapp.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.swapnil.chatapp.composables.ChatAppBar
import com.swapnil.chatapp.composables.SignInWithGoogleButton
import com.swapnil.chatapp.ui.Screen

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val context = LocalContext.current
    Scaffold(topBar = {
        ChatAppBar(title = "Welcome to Chat App")
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            SignInWithGoogleButton(modifier = Modifier, onSuccess = { firebaseUser ->
                val email = firebaseUser.email ?: error("Email not found!")
                viewModel.onLoggedIn(email = email, navController)
            }, onFailure = { error ->
                Toast.makeText(context, "Error is ${error?.message}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
