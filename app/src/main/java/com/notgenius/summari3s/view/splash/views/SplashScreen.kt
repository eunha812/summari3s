package com.notgenius.summari3s.view.splash.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashScreen(updateUI: () -> Unit) {
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

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen {}
}