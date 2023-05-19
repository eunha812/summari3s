package com.notgenuis.summari3s.view.onboard.views

import android.content.Intent
import android.service.autofill.OnClickAction
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
import com.notgenuis.summari3s.R
import com.notgenuis.summari3s.view.config.ConfigurationActivity
import com.notgenuis.summari3s.view.ui.theme.keyColor1

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
                contentDescription = "onBoarding3Text"
            )
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LottieLoader(url = "https://assets7.lottiefiles.com/packages/lf20_M1E6rGUbZs.json")
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