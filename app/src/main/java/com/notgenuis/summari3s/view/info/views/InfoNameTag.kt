package com.notgenuis.summari3s.view.info.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoNameTag(name: String, onClick : () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = Color.White.copy(alpha = 0.4f),
                shape = RoundedCornerShape(50.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            name,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 15.dp)
        )
    }
}