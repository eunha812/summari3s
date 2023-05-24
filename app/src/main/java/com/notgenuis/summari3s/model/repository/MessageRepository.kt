package com.notgenuis.summari3s.model.repository

import androidx.lifecycle.LiveData
import com.notgenuis.summari3s.model.ApiResult
import com.notgenuis.summari3s.model.local.entity.MessageEntity

interface MessageRepository {
    suspend fun createSummary(address: String, message: String): ApiResult<String>

    fun getMessages() : LiveData<MutableList<MessageEntity>>

    suspend fun updateMessage(entity: MessageEntity)

    suspend fun deleteMessage(id: Long)
}