package com.notgenius.summari3s.view.splash.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.notgenius.summari3s.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun MainSplashScreen(updateUI: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        withContext(Dispatchers.Main) {
            updateUI()
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (typo, logoTypo) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.typo),
            modifier = Modifier.constrainAs(typo) {
                start.linkTo(parent.start, 40.dp)
                top.linkTo(parent.top, 75.dp)
            },
            contentDescription = "타이포"
        )

        Image(
            painter = painterResource(id = R.drawable.logo_typo),
            modifier = Modifier.constrainAs(logoTypo) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            contentDescription = "타이포 로고"
        )
    }
}