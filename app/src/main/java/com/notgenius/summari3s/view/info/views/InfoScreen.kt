package com.notgenius.summari3s.view.info.views

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenius.summari3s.R
import com.notgenius.summari3s.utils.readRawTextFile
import com.notgenius.summari3s.view.config.views.*
import com.notgenius.summari3s.view.common.TopContent
import com.notgenius.summari3s.view.info.InfoActivity
import com.notgenius.summari3s.view.ui.theme.keyColorDark1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoScreen() {
    val context = LocalContext.current as InfoActivity

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    var text by remember { mutableStateOf("") }

    val onBackPressed: () -> Unit = {
        if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            context.finish()
        } else {
            scope.launch {
                bottomSheetState.hide()
            }
        }
    }

    BackHandler(onBack = onBackPressed)

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start
                    )
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
            topBar = { TopContent(title = "앱 정보") }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    InfoButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp), description = "이용 약관"
                    ) {
                        text = readRawTextFile(context, R.raw.temrs_of_service)
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    InfoButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp), description = "개인 정보 처리 방침"
                    ) {
                        text = readRawTextFile(context, R.raw.privacy_policy)
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    InfoButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp), description = "오픈소스 라이선스"
                    ) {
                        text = readRawTextFile(context, R.raw.open_source_licenses)
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBB\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB Not Genius",
                        style = TextStyle(
                            color = keyColorDark1,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(
                        color = Color.Transparent,
                        thickness = 3.dp,
                        modifier = Modifier.background(
                            color = Color.Black.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(100.dp)
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        InfoNameTag(name = "김은하") {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/eunha812")))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        InfoNameTag(name = "최재훈") {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/becl3ver")))
                        }
                    }
                }
            }
        }
    }
}