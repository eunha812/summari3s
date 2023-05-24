package com.notgenuis.summari3s.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun Summari3sTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content,
        colors = lightColors(
            background = backgroundColor1,
            surface = keyColorLight1,
            primary = keyColor1,
            onPrimary = keyColorDark1,
            secondary = secondaryColor1
        )
    )
}