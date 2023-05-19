package com.notgenuis.summari3s.view.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Summari3sTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content,
        colors = lightColors(
            background = backgroundColor1,
            surface = keyColorLight1,
            onSurface = keyColorDark1,
            primary = keyColor1,
            onPrimary = keyColorDark1,
            secondary = secondaryColor1
//            primary = keyColor1,
//            primaryVariant = keyColorDark1,
//            onPrimary = Color.White,
//            secondary = secondaryColor1,
//            secondaryVariant = secondaryColorDark1,
//            onSecondary = Color.White,
//            error = Color(0xFFFF4B23),
//            background = backgroundColor1,
        )
    )
}