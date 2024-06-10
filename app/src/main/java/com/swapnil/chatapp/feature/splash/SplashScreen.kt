package com.swapnil.chatapp.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swapnil.chatapp.R
import com.swapnil.chatapp.ui.theme.VerticalLinearGradient

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = VerticalLinearGradient
            ), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = "Chat icon",
            modifier = Modifier.size(96.dp),
            colorFilter = ColorFilter.tint(
                Color.White
            )
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
