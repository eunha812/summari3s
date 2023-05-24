package com.notgenuis.summari3s.model.repository

import com.notgenuis.summari3s.model.ApiResult
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun createSummary(address: String, message: String): ApiResult<String>

    fun getMessages() : Flow<List<MessageEntity>>

    suspend fun updateMessage(entity: MessageEntity) : ApiResult<String>

    suspend fun deleteMessage(id: Long)
}