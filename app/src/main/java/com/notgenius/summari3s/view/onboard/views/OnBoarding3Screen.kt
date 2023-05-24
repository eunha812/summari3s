package com.notgenius.summari3s.view.onboard.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notgenius.summari3s.R
import com.notgenius.summari3s.view.ui.theme.keyColor1

@Composable
fun OnBoarding3Screen(onClickAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFDBDBDA)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 120.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(id = R.drawable.onboarding3text),
                contentDescription = "3줄 요약으로 삶의 질 향상을 느껴보세요."
            )
        }

        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LottieLoader(source = R.raw.onboarding_3, modifier = Modifier.align(Alignment.Center).fillMaxWidth())
            }

            Button(
                onClick = onClickAction,
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp, start = 25.dp, end = 25.dp)
                    .background(keyColor1, shape = RoundedCornerShape(CornerSize(5.dp)))
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = "시작하기", color = Color.White)
            }
        }
    }
}