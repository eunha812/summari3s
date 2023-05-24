package com.notgenius.summari3s.view.history.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenius.summari3s.R
import com.notgenius.summari3s.model.local.entity.MessageEntity
import com.notgenius.summari3s.view.ui.theme.secondaryColor1


@Composable
fun ErrorRow(message: MessageEntity, onClick: () -> Unit) {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.error_icon),
                tint = secondaryColor1,
                contentDescription = "에러 메시지"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                "요약에 실패한 메시지입니다.",
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