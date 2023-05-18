package com.notgenuis.summari3s.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.R
import com.notgenuis.summari3s.view.config.ConfigurationActivity
import com.notgenuis.summari3s.view.onboard.OnBoardingActivity
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import com.notgenuis.summari3s.view.ui.theme.keyColor1
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = keyColor1
                ) {
                    SplashScreen {
                        updateUI()
                    }
                }
            }
        }
    }

    private fun updateUI() {
        if (App.pref.isOnBoardingShowed()) {
            startActivity(Intent(this, ConfigurationActivity::class.java))
            finish()
        } else {
            App.pref.checkOnBoardingShowed()
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
    }
}

@Composable
private fun SplashScreen(updateUI: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lottie_splash_screen") {
        composable("lottie_splash_screen") {
            LottieSplashScreen {
                navController.navigate("main_splash_screen")
            }
        }

        composable("main_splash_screen") {
            MainSplashScreen {
                updateUI()
            }
        }
    }
}

@Composable
private fun LottieSplashScreen(navigateToNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3500)
        withContext(Dispatchers.Main) {
            navigateToNext()
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (button) = createRefs()

        Box(modifier = Modifier
            .constrainAs(button) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .size(125.dp)) {
            SplashLottieLoader()
        }
    }
}

@Composable
fun SplashLottieLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    LottieAnimation(composition = composition, speed = 1.3f)
}

@Composable
private fun MainSplashScreen(updateUI: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
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

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen {}
}