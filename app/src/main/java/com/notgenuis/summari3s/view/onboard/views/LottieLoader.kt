package com.notgenuis.summari3s.view.onboard.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieLoader(url: String) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(url))
    LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}