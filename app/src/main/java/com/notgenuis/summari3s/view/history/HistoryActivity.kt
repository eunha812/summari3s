package com.notgenuis.summari3s.view.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.notgenuis.summari3s.view.history.views.*
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import com.notgenuis.summari3s.view.ui.theme.backgroundColor1
import com.notgenuis.summari3s.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notiID = intent.getLongExtra("id", -1L)

        setContent {
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor1
                ) {
                    HistoryScreen(viewModel, notiID)
                }
            }
        }
    }
}