package com.notgenuis.summari3s.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.notgenuis.summari3s.model.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM message ORDER BY id DESC")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Query("SELECT * FROM message WHERE id = (:id)")
    fun getMessage(id: Long): LiveData<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(entity: MessageEntity)

    @Update
    suspend fun updateMessage(entity: MessageEntity)

    @Query("DELETE FROM message WHERE id = (:id)")
    suspend fun deleteMessageById(id: Long)

    @Query("SELECT * FROM message ORDER BY id DESC LIMIT 1")
    fun getLastMessage() : MessageEntity?
}