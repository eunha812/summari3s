package com.notgenuis.summari3s.view.config.views

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.R
import com.notgenuis.summari3s.view.config.*
import com.notgenuis.summari3s.view.history.HistoryActivity
import com.notgenuis.summari3s.view.info.InfoActivity
import com.notgenuis.summari3s.view.ui.theme.keyColor1
import com.notgenuis.summari3s.view.ui.theme.keyColor2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview
fun ConfigurationScreenPreview() {
    ConfigurationScreen(false)
}

@Composable
fun ConfigurationFab() {
    val context = LocalContext.current as ConfigurationActivity

    Box(modifier = Modifier
        .background(shape = CircleShape, color = keyColor1)
        .size(50.dp)
        .clip(CircleShape)
        .clickable {
            context.startActivity(Intent(context, HistoryActivity::class.java))
        }) {
        Image(
            painter = painterResource(id = R.drawable.logo_typo),
            contentDescription = "요약 메시지 목록 FAB",
            modifier = Modifier
                .align(
                    Alignment.Center
                )
                .size(25.dp)
        )
    }
}

@Composable
fun ConfigurationStateLottie(isOn: Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(if (isOn) R.raw.shiba_run else R.raw.shiba_relax))

    Box(modifier = Modifier
        .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.shiba_background),
            contentDescription = "말풍선",
            modifier = Modifier
                .size(255.dp)
                .align(Alignment.Center)
        )

        LottieAnimation(
            composition = composition,
            speed = 1.5f,
            modifier = Modifier
                .align(Alignment.Center)
                .size(150.dp),
            iterations = LottieConstants.IterateForever
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfigurationScreen(
    isModeOn: Boolean
) {
    val context = LocalContext.current as ConfigurationActivity

    var isOn by remember { mutableStateOf(isModeOn) }

    val defaultSheetContent: @Composable () -> Unit = { Column(modifier = Modifier.height(1.dp)) {} }
    var sheetContent by remember { mutableStateOf<@Composable () -> Unit>(defaultSheetContent) }

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    LaunchedEffect(bottomSheetState.currentValue) {
        if(bottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            sheetContent = defaultSheetContent
        }
    }

    val hideSheet: () -> Unit = {
        scope.launch {
            bottomSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            sheetContent()
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = { ConfigurationFab() }
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(55.dp))
                ConfigurationStateLottie(isOn)
                Spacer(modifier = Modifier.height(10.dp))
                Switch(
                    checked = isOn,
                    onCheckedChange = {
                        isOn = !isOn
                        App.pref.toggleMode()
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = keyColor1,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = keyColor2,
                        uncheckedTrackColor = Color.Gray,
                    )
                )
                Text(text = "3줄요약", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "문자 요약 기능을 키고 끌 수 있습니다.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(30.dp))
                ConfigurationButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    id = R.drawable.ic_wrench,
                    "엔진 선택"
                ) {
                    scope.launch {
                        delay(100)
                        sheetContent = { ConfigurationEngineBottomSheet(hideSheet) }
                        bottomSheetState.show()
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ConfigurationButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    id = R.drawable.ic_funnel,
                    "강도"
                ) {
                    scope.launch {
                        delay(100)
                        sheetContent = { ConfigurationStrengthBottomSheet(hideSheet) }
                        bottomSheetState.show()
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ConfigurationButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    id = R.drawable.info,
                    "앱 정보"
                ) {
                    context.startActivity(Intent(context, InfoActivity::class.java))
                }
            }
        }
    }
}