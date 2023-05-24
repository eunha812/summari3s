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
        viewModelScope.launch {
            repository.deleteMessage(id)
        }
    }

    fun updateMessage(entity: MessageEntity, onFail: () -> Unit) {
        viewModelScope.launch {
            val result = repository.updateMessage(entity)

            if (result !is ApiResult.Success) {
                withContext(Dispatchers.IO) {
                    onFail()
                }
            }
        }
    }
}