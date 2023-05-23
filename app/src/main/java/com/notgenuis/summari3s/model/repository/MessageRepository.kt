package com.notgenuis.summari3s.model.repository

import com.notgenuis.summari3s.model.ApiResult

interface MessageRepository {
    suspend fun getSummaries(message: String): ApiResult<String>

    fun save(senderNumber: String, origin: String, result: String?)
}