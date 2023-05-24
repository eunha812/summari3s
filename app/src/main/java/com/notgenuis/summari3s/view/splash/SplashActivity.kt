package com.notgenuis.summari3s.view.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.view.config.ConfigurationActivity
import com.notgenuis.summari3s.view.history.HistoryActivity
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

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun updateUI() {
        if (App.pref.isOnBoardingShowed()) {
            startActivity(Intent(this, ConfigurationActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
    }
}
