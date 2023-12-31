package com.notgenius.summari3s.view.onboard

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.notgenius.summari3s.App
import com.notgenius.summari3s.view.config.ConfigurationActivity
import com.notgenius.summari3s.view.onboard.views.OnBoarding1Screen
import com.notgenius.summari3s.view.onboard.views.OnBoarding2Screen
import com.notgenius.summari3s.view.onboard.views.OnBoarding3Screen
import com.notgenius.summari3s.view.ui.theme.Summari3sTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current as OnBoardingActivity
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        val pageCount = 3
                        val pageState = rememberPagerState()

                        HorizontalPager(
                            modifier = Modifier.fillMaxSize(),
                            count = pageCount,
                            state = pageState
                        ) { page ->
                            when (page) {
                                0 -> OnBoarding1Screen()
                                1 -> OnBoarding2Screen()
                                2 -> OnBoarding3Screen {
                                    App.pref.checkOnBoardingShowed()
                                    startActivity(
                                        Intent(
                                            context,
                                            ConfigurationActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                            }
                        }


                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(bottom = 60.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            HorizontalPagerIndicator(
                                pagerState = pageState,
                                activeColor = Color.White.copy(alpha = 0.9f),
                                inactiveColor = Color.White.copy(alpha = 0.4f)
                            )
                        }
                    }
                }
            }
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}


