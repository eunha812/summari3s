package com.notgenius.summari3s.view.onboard.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieLoader(source : Int, modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(source))
    LottieAnimation(modifier = modifier, composition = composition, iterations = LottieConstants.IterateForever)
}