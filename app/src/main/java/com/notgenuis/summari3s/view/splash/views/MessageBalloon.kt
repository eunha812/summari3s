package com.notgenuis.summari3s.view.splash.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notgenuis.summari3s.R

@Composable
fun MessageBalloon(modifier: Modifier, isLeft: Boolean, visible: Boolean) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = visible,
        exit = slideOutHorizontally(
            targetOffsetX = {
                with(density) { if (isLeft) 1000.dp.roundToPx() else -1000.dp.roundToPx() }
            },
            animationSpec = tween(durationMillis = 1500)
        ),
        modifier = modifier.wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = if (isLeft) R.drawable.message_balloon_left else R.drawable.messge_balloon_right),
            contentDescription = "말풍선"
        )
    }
}