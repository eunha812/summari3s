package com.notgenius.summari3s.view.config.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenius.summari3s.R
import com.notgenius.summari3s.model.ModelType
import com.notgenius.summari3s.view.ui.theme.keyColor1

@Composable
fun ConfigurationRadioButton(
    modifier: Modifier,
    modelType: ModelType,
    currentModelType: ModelType,
    setModelType: (ModelType) -> Unit
) {
    Row(
        modifier = modifier.clickable { setModelType(currentModelType) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = modelType == currentModelType,
            onClick = { setModelType(currentModelType) },
            colors = RadioButtonDefaults.colors(
                selectedColor = keyColor1
            )
        )
        Spacer(modifier = Modifier.width(28.dp))
        Text(
            text = if (currentModelType == ModelType.CHAT_GPT) "ChatGPT" else "Bard",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ConfigurationButton(
    modifier: Modifier, @DrawableRes id: Int, description: String, onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)

    Box(modifier = modifier
        .clickable {
            onClick()
        }
        .background(color = Color.White, shape = shape)
        .clip(shape)) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.dp, top = 15.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = id), contentDescription = description)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = description, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "화살표",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 40.dp)
        )
    }
}