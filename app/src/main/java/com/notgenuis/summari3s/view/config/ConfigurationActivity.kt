package com.notgenuis.summari3s.view.config

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.notgenuis.summari3s.model.repository.MessageRepositoryImpl
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

private const val TAG = "구글_ConfigurationActivity"
@AndroidEntryPoint
class ConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Summari3sTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text(text = "Configuration")
                    LaunchedEffect(Unit) {
                        callImpl()
                    }
                }
            }
        }
    }

    suspend fun callImpl() {
        withContext(Dispatchers.IO) {

            MessageRepositoryImpl().getSummaries("[모바일 실습 장비 반납 안내]\n" +
                    "6반 여러분,\n" +
                    "1학기가 마무리 되어감에 따라, 실습장비 반납 안내드립니다. \n" +
                    "\n" +
                    "ㅁ 반납 일시 : 5/26(금) 13:20~14:00\n" +
                    "ㅁ 반납 장소 : 강의장\n" +
                    "ㅁ 반납 장비 : \n" +
                    " -노트북 & 구성품(가방, 가방끈, 마우스, 마우스패드)\n" +
                    " -S10, 충전기(케이블 포함), Beacon센서, BLE센서, NFC Tag 2장\n" +
                    "\n" +
                    " :star: 반납 전 해야 할 일\n" +
                    "\n" +
                    "보관 중이던 수령확인서 2부 하단에 반납일 기재/본인 이름 작성\n" +
                    "노트북 가방 속 쓰레기 버리기 & 노트북 물티슈로 닦기\n" +
                    "S10: 구글계정, 삼성계정 로그아웃 후 공장 초기화\n" +
                    "Beacon센서, BLE센서 정상 작동 확인 (교수님 가이드에 따라 전체 동시 점검)\n" +
                    ":round_pushpin: 자세한 내용은 첨부된 반납 가이드 참고해 주세요. \n" +
                    "\n" +
                    ":round_pushpin: 당일 모든 장비 정상 여부 확인 후 반납 대장 기록 및 수령확인서 제출 예정입니다.\n" +
                    ":round_pushpin: 반납 당일 오후 일정으로 시간이 많지 않으니, 미리미리 준비해 주시기 바랍니다.")
        }
    }
}