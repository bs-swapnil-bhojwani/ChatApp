package com.swapnil.chatapp.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun TextWithAsterisk(text: String) {
    Text(text = buildAnnotatedString {
        append("$text ")
        withStyle(style = SpanStyle(Color.Red)) {
            append("*")
        }
    })
}