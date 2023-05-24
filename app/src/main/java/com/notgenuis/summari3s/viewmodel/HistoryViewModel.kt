package com.notgenuis.summari3s.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notgenuis.summari3s.model.ApiResult
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import com.notgenuis.summari3s.model.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: MessageRepository) : ViewModel() {
    val messages = repository.getMessages()

    fun deleteMessage(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMessage(id)
        }
    }

    fun updateMessage(entity: MessageEntity, onSuccess: () -> Unit, onFail: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.updateMessage(entity)

            withContext(Dispatchers.Main) {
                if (result is ApiResult.Success) {
                    onSuccess.invoke()
                } else {
                    onFail.invoke()
                }
            }
        }
    }
}