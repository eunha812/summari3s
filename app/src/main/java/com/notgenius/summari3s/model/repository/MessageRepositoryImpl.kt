package com.notgenius.summari3s.model.repository

import com.notgenius.summari3s.BuildConfig
import com.notgenius.summari3s.App
import com.notgenius.summari3s.model.ApiResult
import com.notgenius.summari3s.model.ModelType
import com.notgenius.summari3s.model.local.dao.MessageDao
import com.notgenius.summari3s.model.local.entity.MessageEntity
import com.notgenius.summari3s.model.remote.dto.*
import com.notgenius.summari3s.model.remote.service.BardServiceImpl
import com.notgenius.summari3s.model.remote.service.ChatGPTServiceImpl
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MessageRepositoryImpl(private val messageDao: MessageDao): MessageRepository {
    companion object {
        private const val BEARER = "Bearer "

        private const val TAG = "MessageRepositoryImpl_낫지니어스"
    }

    override suspend fun createSummary(address: String, message: String): Pair<ApiResult<String>,Long> {
        val result = if(App.pref.getModelType() == ModelType.GOOGLE) getGoogleResponse(message)
        else getChatGPTResponse(message)

        if(result is ApiResult.Success) {
            insertMessage(address, message, result.data)
        } else {
            insertMessage(address, message, null)
        }

        val lastId = messageDao.getLastMessage()?.id ?: -1

        return Pair(result, lastId)
    }

    override fun getMessages(): Flow<List<MessageEntity>> {
        return messageDao.getAllMessages()
    }

    override suspend fun updateMessage(entity: MessageEntity) : ApiResult<String> {
        val result = if(App.pref.getModelType() == ModelType.GOOGLE) getGoogleResponse(entity.origin)
        else getChatGPTResponse(entity.origin)

        if(result is ApiResult.Success) {
            val newEntity = MessageEntity(
                entity.id,
                entity.senderNumber,
                entity.date,
                entity.origin,
                result.data
            )

            messageDao.updateMessage(newEntity)
        }

        return result
    }

    override suspend fun deleteMessage(id: Long) {
        messageDao.deleteMessageById(id)
    }

    private suspend fun insertMessage(address: String, message: String, summary: String?) {
        val entity = MessageEntity(
            address, getDate(), message, summary
        )

        messageDao.insertMessage(entity)
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

    private fun getDate() : String{
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.KOREA)
        return dateFormat.format(currentDate)
    }
}