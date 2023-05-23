package com.notgenuis.summari3s.view.config

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import com.notgenuis.summari3s.view.ui.theme.backgroundColor1
import dagger.hilt.android.AndroidEntryPoint
import com.notgenuis.summari3s.view.config.views.ConfigurationScreen

@AndroidEntryPoint
class ConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor1
                ) {
                    ConfigurationScreen(
                        App.pref.isModeOn()
                    )
                }
            }
        }
    }
}











