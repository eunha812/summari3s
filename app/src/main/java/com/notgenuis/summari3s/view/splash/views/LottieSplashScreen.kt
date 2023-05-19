package com.notgenuis.summari3s.view.splash.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.notgenuis.summari3s.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun LottieSplashScreen(navigateToNext: () -> Unit) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1000)
        visible = false
        delay(3500)
        withContext(Dispatchers.Main) {
            navigateToNext()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(125.dp)
        ) {
            SplashLottieLoader()
        }

        MessageBalloon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 100.dp, start = 25.dp),
            isLeft = true,
            visible = visible
        )

        MessageBalloon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 25.dp),
            isLeft = false,
            visible = visible
        )

        MessageBalloon(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 100.dp, start = 25.dp),
            isLeft = true,
            visible = visible
        )

        MessageBalloon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 50.dp, end = 25.dp),
            isLeft = false,
            visible = visible
        )
    }
}

@Composable
fun SplashLottieLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    LottieAnimation(composition = composition, speed = 1.3f)
}