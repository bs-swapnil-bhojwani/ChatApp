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
import androidx.navigation.NavHostController
import com.swapnil.chatapp.composables.ChatAppBar
import com.swapnil.chatapp.composables.SignInWithGoogleButton

@Composable
fun LoginScreen(navController: NavHostController) {
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
                navController.navigate("EditProfile?email=${firebaseUser.email}")
                Toast.makeText(context, "User is ${firebaseUser.email}", Toast.LENGTH_SHORT)
                    .show()
            }, onFailure = { error ->
                Toast.makeText(context, "Error is ${error?.message}", Toast.LENGTH_SHORT).show()
            })
        }
    }
}
