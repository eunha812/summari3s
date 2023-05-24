package com.notgenius.summari3s.view.history.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BorderButton(onClick: () -> Unit, content: String, modifier: Modifier) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            content, style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun ColoredButton(onClick: () -> Unit, color: Color, content: String, modifier: Modifier) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = Color.White),
        border = BorderStroke(0.dp, Color.Transparent),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            content, style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}