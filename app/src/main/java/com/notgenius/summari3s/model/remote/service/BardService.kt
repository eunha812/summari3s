package com.notgenius.summari3s.model.remote.service

import com.notgenius.summari3s.model.remote.dto.BardRequestDto
import com.notgenius.summari3s.model.remote.dto.BardResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BardService {
    @POST("/chat")
    suspend fun getSummaries(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String = "text/plain",
        @Body body: BardRequestDto
    ): Response<BardResponseDto>
}