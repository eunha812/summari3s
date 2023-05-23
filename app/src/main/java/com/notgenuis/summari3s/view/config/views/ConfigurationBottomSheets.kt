package com.notgenuis.summari3s.view.config.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.model.ModelType
import com.notgenuis.summari3s.model.SummaryStrength
import com.notgenuis.summari3s.view.common.CancelButton
import com.notgenuis.summari3s.view.common.SubmitButton

@Composable
fun ConfigurationEngineBottomSheet(hideSheet: () -> Unit) {
    val (modelType, setModelType) = remember { mutableStateOf(App.pref.getModelType()) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "엔진을 선택하세요.", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(28.dp))
        Text(text = "문자를 요약할 엔진을 직접 선택할 수 있습니다.", fontWeight = FontWeight.Normal, fontSize = 13.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        ConfigurationRadioButton(Modifier.fillMaxWidth(), modelType, ModelType.CHAT_GPT, setModelType)
        ConfigurationRadioButton(Modifier.fillMaxWidth(), modelType, ModelType.GOOGLE, setModelType)
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CancelButton(modifier = Modifier
                .weight(1f)
                .height(40.dp), text = "취소") {
                hideSheet()
            }
            Spacer(modifier = Modifier.width(10.dp))
            SubmitButton(modifier = Modifier
                .weight(1f)
                .height(40.dp), text = "확인") {
                App.pref.setModelType(modelType)
                hideSheet()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ConfigurationStrengthBottomSheet(hideSheet: () -> Unit) {
    val (strengthType, setStrength) = remember { mutableStateOf(App.pref.getStrength()) }
    val position = remember { mutableStateOf(strengthType.ordinal) }

    LaunchedEffect(position.value) {
        setStrength(when(position.value) {
            0 -> SummaryStrength.LOW
            1 -> SummaryStrength.MEDIUM
            2 -> SummaryStrength.HIGH
            else -> SummaryStrength.MAX
        })
    }

    Column(modifier = Modifier
        .fillMaxWidth()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "요약 강도를 선택하세요.", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "문자를 요약할 텍스트 수를 지정할 수 있습니다.",
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
        ConfigurationStepProgressBar(position = position)
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                CancelButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp), text = "취소"
                ) {
                    hideSheet()
                }
                Spacer(modifier = Modifier.width(10.dp))
                SubmitButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp), text = "확인"
                ) {
                    App.pref.setStrength(strengthType)
                    hideSheet()
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}