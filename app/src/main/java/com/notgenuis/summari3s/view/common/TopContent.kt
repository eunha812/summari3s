package com.notgenuis.summari3s.view.history.views

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenuis.summari3s.R
import com.notgenuis.summari3s.view.history.HistoryActivity
import com.notgenuis.summari3s.view.ui.theme.backgroundColor1
import com.notgenuis.summari3s.view.ui.theme.keyColorDark1

@Composable
fun TopContent(title : String) {
    val context = LocalContext.current as ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(backgroundColor1),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = {
            context.finish()
        }) {
            Icon(
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "뒤로가기 버튼"
            )
        }
        Text(
            text = title,
            modifier = Modifier.padding(start = 15.dp),
            color = keyColorDark1,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
        )
    }
}