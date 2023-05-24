package com.notgenius.summari3s.view.config.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenius.summari3s.view.ui.theme.backgroundColor1
import com.notgenius.summari3s.view.ui.theme.keyColor1
import com.notgenius.summari3s.view.ui.theme.keyColorLight2

data class StrengthLevel(
    val words: String,
    val name: String
)

@Composable
fun ConfigurationStepProgressBar(
    position: MutableState<Int>
) {
    val items = mutableListOf(
        StrengthLevel("20 단어 이내", "Low"),
        StrengthLevel("40 단어 이내", "Medium"),
        StrengthLevel("60 단어 이내", "High"),
        StrengthLevel("100 단어 이내", "Max")
    )

    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(items.size) {
                    Column(
                        Modifier
                            .wrapContentWidth()
                            .clickable(
                                interactionSource = interactionSource, indication = null
                            ) {
                                position.value = it
                            }, horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = items[it].words,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            color = if(position.value == it) keyColorLight2 else Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = items[it].name,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (position.value == it) keyColor1 else Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = if (it <= position.value) keyColor1 else backgroundColor1,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            val width = drawContext.size.width
            val height = drawContext.size.height

            val yOffset = height / 2
            val itemWidth = width / items.size

            var startOffset = itemWidth / 2
            var endOffset = startOffset

            repeat(items.size - 1) {
                endOffset += itemWidth
                if(it + 1 <= position.value) {
                    drawLine(
                        start = Offset(startOffset, yOffset),
                        end = Offset(endOffset, yOffset),
                        strokeWidth = 4.dp.toPx(),
                        color = keyColor1
                    )
                }
                startOffset = endOffset
            }
        }
    }
}

@Preview
@Composable
fun ConfigurationStepProgressBarPreview() {
    ConfigurationStepProgressBar(position = remember { mutableStateOf(3) })
}