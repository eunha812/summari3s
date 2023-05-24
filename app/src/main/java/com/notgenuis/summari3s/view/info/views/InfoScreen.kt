package com.notgenuis.summari3s.view.info.views

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.R
import com.notgenuis.summari3s.view.config.ConfigurationActivity
import com.notgenuis.summari3s.view.config.views.*
import com.notgenuis.summari3s.view.history.views.TopContent
import com.notgenuis.summari3s.view.info.InfoActivity
import com.notgenuis.summari3s.view.ui.theme.keyColor1
import com.notgenuis.summari3s.view.ui.theme.keyColor2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoScreen() {
    val context = LocalContext.current as InfoActivity

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val text by remember { mutableStateOf("") }

    val hideSheet: () -> Unit = {
        scope.launch {
            bottomSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(text)
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
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                InfoButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), description = "이용 약관") {

                }
                Spacer(modifier = Modifier.height(10.dp))
                InfoButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), description = "개인 정보 처리 방침") {

                }
                Spacer(modifier = Modifier.height(10.dp))
                InfoButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), description = "오픈소스 라이선스") {

                }
            }
        }
    }
}