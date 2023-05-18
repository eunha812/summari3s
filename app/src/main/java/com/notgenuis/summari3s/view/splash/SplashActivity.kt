package com.notgenuis.summari3s.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.view.config.ConfigurationActivity
import com.notgenuis.summari3s.view.onboard.OnBoardingActivity
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Button(onClick = { updateUI() }, modifier = Modifier.wrapContentSize()) {
                        Text(text = "Splash")
                    }
                }
            }
        }
    }

    private fun updateUI() {
        if(App.pref.isOnBoardingShowed()) {
            startActivity(Intent(this, ConfigurationActivity::class.java))
            finish()
        } else {
            App.pref.checkOnBoardingShowed()
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
    }
}