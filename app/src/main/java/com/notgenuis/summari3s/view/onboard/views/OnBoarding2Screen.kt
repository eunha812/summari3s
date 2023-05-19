package com.notgenuis.summari3s.view.onboard.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notgenuis.summari3s.R

@Composable
fun OnBoarding2Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1C1E37)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 120.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(id = R.drawable.onboarding2text),
                contentDescription = "onBoarding2Text"
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth().padding(bottom = 60.dp)
        ) {
            LottieLoader(url = "https://assets2.lottiefiles.com/packages/lf20_iFO6UB9JpP.json")
        }
    }
}