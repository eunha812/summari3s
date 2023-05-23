package com.notgenuis.summari3s.model.repository

import android.util.Log
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.BuildConfig
import com.notgenuis.summari3s.model.ApiResult
import com.notgenuis.summari3s.model.ModelType
import com.notgenuis.summari3s.model.remote.dto.*
import com.notgenuis.summari3s.model.remote.service.BardServiceImpl
import com.notgenuis.summari3s.model.remote.service.ChatGPTServiceImpl
import retrofit2.Response

private const val TAG = "구글_MessageRepositoryImpl"
class MessageRepositoryImpl: MessageRepository {
    companion object {
        private const val BEARER = "Bearer "
    }

    override suspend fun getSummaries(message: String): ApiResult<String> {
        return if(App.pref.getModelType() == ModelType.GOOGLE) getGoogleResponse(message)
        else getChatGPTResponse(message)
    }

    private suspend fun getChatGPTResponse(message: String) : ApiResult<String> {
        val power = App.pref.getStrength().power
        val order = "이 메시지 ${power}글자 이내로 요약해줘\\n\\n"

        val requestMessage = ChatGPTRequestMessage(
            role = "user",
            content = order + message
        )

        val dto = ChatGPTRequestDto(
            messages = mutableListOf(requestMessage)
        )

        val response: Response<ChatGPTResponseDto>

        try {
            response = ChatGPTServiceImpl.getService().getSummaries(
                token = BEARER + BuildConfig.CHAT_GPT_API_KEY,
                body = dto
            )
        } catch (e: Exception) {
            return ApiResult.Exception(e)
        }

        return if(response.isSuccessful) {
            try {
                ApiResult.Success(response.body()!!.choices[0].message.content)
            } catch (e: Exception) {
                ApiResult.Exception(e)
            }
        } else {
            ApiResult.Error(response.code(), response.message())
        }
    }

    private suspend fun getGoogleResponse(message: String) : ApiResult<String> {
        val power = App.pref.getStrength().power
        val order = "이 메시지 ${power}글자 이내로 요약한 내용 알려줘\\n\\n"

        val dto = BardRequestDto(
            input = order + message
        )

        val response : Response<BardResponseDto>

        try {
            response = BardServiceImpl.getService().getSummaries(
                token = BEARER + BuildConfig.BARD_API_KEY,
                body = dto
            )
        } catch (e: Exception) {
            return ApiResult.Exception(e)
        }

        return if (response.isSuccessful) {
            try {
                ApiResult.Success(response.body()!!.output)
            } catch (e: Exception) {
                ApiResult.Exception(e)
            }
        } else {
            ApiResult.Error(response.code(), response.message())
        }
    }
}