package com.notgenius.summari3s.view.onboard.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notgenius.summari3s.R

@Composable
fun OnBoarding1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFC7D9E8)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 120.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(id = R.drawable.onboarding1text),
                contentDescription = "중요한 문자를 한눈에 3줄 요약으로 보여드립니다."
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
        ) {
            LottieLoader(source = R.raw.onboarding_1, modifier = Modifier.align(Alignment.Center).fillMaxWidth())
        }
    }
}