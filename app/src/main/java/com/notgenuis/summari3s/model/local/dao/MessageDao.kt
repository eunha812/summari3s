package com.notgenuis.summari3s.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.notgenuis.summari3s.model.local.entity.MessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM message")
    fun getAllMessages(): LiveData<MutableList<MessageEntity>>

    @Query("SELECT * FROM message where id = (:id)")
    fun getMessage(id: Long): LiveData<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(entity: MessageEntity)

    @Update
    suspend fun updateMessage(entity: MessageEntity)

    @Query("DELETE FROM message WHERE id = (:id)")
    suspend fun deleteMessageById(id: Long)
}