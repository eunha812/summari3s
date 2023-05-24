package com.notgenuis.summari3s.view.history.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import com.notgenuis.summari3s.view.ui.theme.keyColor1

@Composable
fun MessageRow(message: MessageEntity, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .coloredShadow(color = Color.Black.copy(0.2f), 10.dp, 5.dp, 0.dp, 0.dp, 1f)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                message.senderNumber,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
            Text(
                message.date,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                    color = keyColor1
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                message.result!!,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    color = Color.Black.copy(alpha = 0.4f)
                )
            )
        }
    }
}