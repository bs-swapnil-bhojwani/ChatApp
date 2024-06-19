package com.swapnil.chatapp.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swapnil.chatapp.composables.ChatAppBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        ChatAppBar(title = "Welcome to Chat Home")
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No chats found!")
        }
    }
}