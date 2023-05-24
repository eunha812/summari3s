package com.notgenius.summari3s.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.notgenius.summari3s.model.local.dao.MessageDao
import com.notgenius.summari3s.model.local.entity.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}