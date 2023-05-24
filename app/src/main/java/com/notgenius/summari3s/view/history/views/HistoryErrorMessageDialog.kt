package com.notgenius.summari3s.view.history.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenius.summari3s.view.ui.theme.keyColor1
import com.notgenius.summari3s.view.ui.theme.secondaryColor1

@Composable
fun HistoryErrorMessageDialog(onRetry: () -> Unit, onClickShowAll: () -> Unit, onClickCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClickCancel,
        title = {
            Text(
                text = "요약 실패한 메시지입니다.",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 19.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = "데이터 및 와이파이 상태를 확인하고 다시 메시지 요약을 시도할 수 있습니다.",
                style = TextStyle(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.5f))
            )
        },
        buttons = {
            Column(modifier = Modifier.fillMaxWidth()) {
                ColoredButton(
                    onClick = onRetry,
                    color = secondaryColor1,
                    content = "요약 재시도",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                ColoredButton(
                    onClick = onClickShowAll,
                    color = keyColor1,
                    content = "전체 내용 보기",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                BorderButton(
                    onClick = onClickCancel,
                    content = "취소",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    )
}