package com.notgenuis.summari3s.model.local.dao

import androidx.room.*
import com.notgenuis.summari3s.model.local.entity.MessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM message")
    suspend fun getAllMessages(): MutableList<MessageEntity>

    @Query("SELECT * FROM message where id = (:id)")
    suspend fun getMessage(id: Long): MessageEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(entity: MessageEntity)

    @Update
    suspend fun updateMessage(entity: MessageEntity)

    @Query("DELETE FROM message WHERE id = (:id)")
    suspend fun deleteMessageById(id: Long)
}