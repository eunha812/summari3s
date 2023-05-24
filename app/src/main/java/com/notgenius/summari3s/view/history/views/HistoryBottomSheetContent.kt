package com.notgenius.summari3s.view.history.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenius.summari3s.model.local.entity.MessageEntity
import com.notgenius.summari3s.view.ui.theme.keyColor1
import com.notgenius.summari3s.view.ui.theme.secondaryColor1

@Composable
fun HistoryBottomSheetContent(message: MessageEntity, onClickCancel: () -> Unit, onClickRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                message.senderNumber,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                message.date,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = keyColor1
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                message.origin,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                ),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                BorderButton(onClick = onClickCancel, content = "취소", modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                ColoredButton(
                    onClick = onClickRemove,
                    color = secondaryColor1,
                    content = "삭제하기",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}