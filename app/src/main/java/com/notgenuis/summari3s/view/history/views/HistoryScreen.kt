package com.notgenuis.summari3s.view.history.views

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import com.notgenuis.summari3s.view.history.HistoryActivity
import com.notgenuis.summari3s.view.ui.theme.backgroundColor1
import com.notgenuis.summari3s.viewmodel.HistoryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {
    val context = LocalContext.current as HistoryActivity
    val list by viewModel.messages.collectAsState(initial = emptyList())

    val defaultSheetContent: @Composable () -> Unit =
        { Column(modifier = Modifier.height(1.dp)) {} }
    var sheetContent by remember { mutableStateOf<@Composable () -> Unit>(defaultSheetContent) }
    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(bottomSheetState.currentValue) {
        if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            sheetContent = defaultSheetContent
        }
    }

    val hideSheet: () -> Unit = {
        scope.launch {
            bottomSheetState.hide()
        }
    }

    var selectedIndex by remember { mutableStateOf(-1) }

    val removeMessage: () -> Unit = {
        if (selectedIndex == -1) {
            Toast.makeText(context, "메시지를 불러오는데에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.deleteMessage(list[selectedIndex].id)
            Toast.makeText(context, "메시지를 삭제하였습니다.", Toast.LENGTH_SHORT).show()
        }
        scope.launch {
            bottomSheetState.hide()
        }
    }


    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedMessage by remember { mutableStateOf(MessageEntity(-1, "", "", "", "")) }

    if (showAlertDialog) {
        HistoryErrorMessageDialog(onRetry = {
            viewModel.updateMessage(list[selectedIndex], {
                showAlertDialog = false
            }) {
                Toast.makeText(context, "요약에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }, onClickShowAll = {
            scope.launch {
                sheetContent = {
                    HistoryBottomSheetContent(
                        message = selectedMessage,
                        onClickCancel = hideSheet,
                        onClickRemove = removeMessage
                    )
                }
                bottomSheetState.show()
            }
            showAlertDialog = false
        }) { showAlertDialog = false }
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
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor1)
                        .padding(vertical = 20.dp)
                ) {
                    TopContent()
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxHeight()
                    ) {
                        itemsIndexed(list) { index, message ->
                            Spacer(modifier = Modifier.height(10.dp))
                            if (message.result == null) {
                                ErrorRow(message = message, onClick = {
                                    selectedIndex = index
                                    selectedMessage = message
                                    showAlertDialog = true
                                })
                            } else {
                                MessageRow(message = message) {
                                    selectedIndex = index
                                    scope.launch {
                                        delay(100)
                                        sheetContent =
                                            {
                                                HistoryBottomSheetContent(
                                                    message = message,
                                                    onClickCancel = hideSheet,
                                                    onClickRemove = removeMessage
                                                )
                                            }
                                        bottomSheetState.show()
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}