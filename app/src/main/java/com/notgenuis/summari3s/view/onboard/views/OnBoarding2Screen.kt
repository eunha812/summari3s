package com.notgenuis.summari3s.view.onboard.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notgenuis.summari3s.R

@Composable
fun OnBoarding2Screen() {
    val screenWidth = (LocalConfiguration.current.screenWidthDp / 1.7).dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181A31)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LottieLoader(source = R.raw.onboarding_2, modifier = Modifier.align(Alignment.Center).fillMaxWidth())
            }

            Box(
                modifier = Modifier.width(screenWidth),
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopStart),
                    painter = painterResource(id = R.drawable.background_circle),
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 180.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(id = R.drawable.onboarding2text),
                contentDescription = "문자 요약 강도를 직접 정할 수 있어요."
            )
        }

    }
}