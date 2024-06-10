package com.swapnil.chatapp.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.swapnil.chatapp.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(title:String) {
    TopAppBar(
        title = { Text(text = title) }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,
            titleContentColor = Color.White
        )
    )
}