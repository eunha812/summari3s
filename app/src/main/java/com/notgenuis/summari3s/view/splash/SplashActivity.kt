package com.notgenuis.summari3s.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.R
import com.notgenuis.summari3s.view.config.ConfigurationActivity
import com.notgenuis.summari3s.view.onboard.OnBoardingActivity
import com.notgenuis.summari3s.view.splash.views.SplashScreen
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import com.notgenuis.summari3s.view.ui.theme.keyColor1
import dagger.hilt.android.AndroidEntryPoint

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
