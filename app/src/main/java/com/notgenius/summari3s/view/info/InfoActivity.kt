package com.notgenius.summari3s.view.info

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.notgenius.summari3s.view.info.views.InfoScreen
import com.notgenius.summari3s.view.ui.theme.Summari3sTheme
import com.notgenius.summari3s.view.ui.theme.backgroundColor1

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor1
                ) {
                    InfoScreen()
                }
            }
        }
    }
}