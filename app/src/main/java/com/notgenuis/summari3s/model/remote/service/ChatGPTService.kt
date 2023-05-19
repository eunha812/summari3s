package com.notgenuis.summari3s.model.remote.service

import com.notgenuis.summari3s.model.remote.dto.ChatGPTRequestDto
import com.notgenuis.summari3s.model.remote.dto.ChatGPTResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatGPTService {
    @POST("/v1/chat/completions")
    suspend fun getSummaries(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String,
        @Body body: ChatGPTRequestDto
    ): Response<ChatGPTResponseDto>
}