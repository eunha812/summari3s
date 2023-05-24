package com.notgenuis.summari3s.view.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import com.notgenuis.summari3s.view.history.views.*
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import com.notgenuis.summari3s.view.ui.theme.backgroundColor1
import dagger.hilt.android.AndroidEntryPoint

private var messageList = mutableListOf<MessageEntity>()

@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initList()

        setContent {
            HistoryScreen(messageList)
        }
    }
}

fun initList() {
    messageList.add(
        MessageEntity(
            "114",
            "2023-05-22 12:33",
            "U+ 장기고객 이벤트 6탄\n" +
                    "[Web발신]\n" +
                    "(광고)[LG U+] <범죄도시3> 전국 시사회 초청 이벤트 안내\n" +
                    "니 내 누군지 아니? 내가 바로 유플러스의 ♥장기고객♥이야!\n" +
                    "공식 개봉 전 먼저 만나는 <범죄도시3> 시사회에 장기고객님을 단독 초대해요:slightly_smiling_face:\n" +
                    "\n" +
                    "▶ 응모 기간\n" +
                    " · 2023년 5월 3일(수) ~ 14일(일)\n" +
                    "\n" +
                    "▶ 응모 방법\n" +
                    " ① 핸드폰번호로 본인 인증하기\n" +
                    " ② 객관식, 주관식 문제 입력하기\n" +
                    " ③ 관람하고 싶은 상영관 선택하면 응모 완료!\n" +
                    " +) SNS 소문내면 당첨 확률이 더더 올라가요↑↑\n" +
                    "\n" +
                    "▼ 이벤트 바로 응모하기\n" +
                    "☞ https://bit.ly/3oo2jSt\n" +
                    "\n" +
                    "■ 이벤트 내용\n" +
                    "① 프라이빗 박스(PRIVATE BOX) 프리미엄관 (2명)\n" +
                    " ※ CGV용산아이파크몰점에 한하여 진행돼요.\n" +
                    "② 씨네드쉐프(CINE de CHEF) 특별관 <티켓 2매+레스토랑 식사> (100명)\n" +
                    " ※ CGV용산아이파크몰/부산 센텀시티점에 한하여 진행돼요.\n" +
                    "③ 영화 티켓 2매+CGV콤보(팝콘L+음료2) (4,000명)\n" +
                    " ※ 무대인사는 \"CGV용산아이파크몰\"에서만 진행돼요. (500명 별도 선정 후 개별 안내)\n" +
                    "\n" +
                    "초청 일자: 2023년 5월 27일(토) 오후 4시~\n" +
                    "초청 상영관: CGV용산아이파크몰/CGV부산센텀시티/CGV대구/CGV대전/CGV광주상무/CGV강릉\n" +
                    "※ 자세한 내용은 이벤트 페이지 내용과 유의사항을 참고해 주세요.\n" +
                    "\n" +
                    "▶문의 또는 무료수신거부: \n" +
                    "U+ 고객센터 080-019-7000 (무료)\n" +
                    "\n" +
                    "▶[만족도 조사]\n" +
                    "https://dcrm.uplus.co.kr/sv.do?dHubId=30o50I91Y13G93t25v05&typeKd=mms",
            "U+ 장기고객 대상 범죄도시3 전국 시사회 초청 이벤트 진행 중!"
        )
    )
    messageList.add(
        MessageEntity(
            "싸피",
            "2023-05-21 18:19",
            "[모바일 실습 장비 반납 안내]\n" +
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
                    ":round_pushpin: 반납 당일 오후 일정으로 시간이 많지 않으니, 미리미리 준비해 주시기 바랍니다.",
            "5/26(금) 13:20~14:00 강의장에서 실습장비 반납. 반납 가이드 참고하여 준비하기."
        )
    )
    messageList.add(MessageEntity("싸피", "2023-05-21-01:45", "내용", null))
    messageList.add(MessageEntity("싸피", "2023-05-21-01:45", "내용", null))
    messageList.add(MessageEntity("싸피", "2023-05-21-01:45", "내용", null))
    messageList.add(MessageEntity("싸피", "2023-05-21-01:45", "내용", null))
    messageList.add(
        MessageEntity(
            "114",
            "2023-05-22 12:33",
            "U+ 장기고객 이벤트 6탄 [Web발신]",
            "U+ 장기고객 대상 범죄도시3 전국 시사회 초청 이벤트 진행 중!"
        )
    )
    messageList.add(
        MessageEntity(
            "싸피",
            "2023-05-21 18:19",
            "[모바일 실습 장비 반납 안내]\n6반 여러분",
            "5/26(금) 13:20~14:00 강의장에서 실습장비 반납. 반납 가이드 참고하여 준비하기."
        )
    )
}




@Composable
@Preview
fun HistoryScreenPreview() {
    Summari3sTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor1)
//                .padding(all = 20.dp)
        ) {
            TopContent()
        }
    }
}