package com.notgenuis.summari3s.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.withTransaction
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.BuildConfig
import com.notgenuis.summari3s.model.ApiResult
import com.notgenuis.summari3s.model.ModelType
import com.notgenuis.summari3s.model.local.database.MessageDatabase
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import com.notgenuis.summari3s.model.remote.dto.*
import com.notgenuis.summari3s.model.remote.service.BardServiceImpl
import com.notgenuis.summari3s.model.remote.service.ChatGPTServiceImpl
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MessageRepositoryImpl(context: Context): MessageRepository {
    private val database : MessageDatabase = Room.databaseBuilder(
        context.applicationContext,
        MessageDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val messageDao = database.messageDao()

    companion object {
        private const val BEARER = "Bearer "
        private const val DATABASE_NAME = "message_database.db"

        private const val TAG = "MessageRepositoryImpl_낫지니어스"
    }

    override suspend fun createSummary(address: String, message: String): ApiResult<String> {
        val result = if(App.pref.getModelType() == ModelType.GOOGLE) getGoogleResponse(message)
        else getChatGPTResponse(message)

        if(result is ApiResult.Success) {
            insertMessage(address, message, result.data)
        } else {
            insertMessage(address, message, null)
        }

        return result
    }

    override fun getMessages(): LiveData<MutableList<MessageEntity>> {
        return messageDao.getAllMessages()
    }

    override suspend fun updateMessage(entity: MessageEntity) {
        database.withTransaction {
            messageDao.updateMessage(entity)
        }
    }

    override suspend fun deleteMessage(id: Long) {
        database.withTransaction {
            messageDao.deleteMessageById(id)
        }
    }

    private suspend fun insertMessage(address: String, message: String, summary: String?) {
        val entity = MessageEntity(
            address, getDate(), message, summary
        )

        database.withTransaction {
            messageDao.insertMessage(entity)
        }
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