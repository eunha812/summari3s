package com.notgenius.summari3s.di

import android.content.Context
import androidx.room.Room
import com.notgenius.summari3s.model.local.dao.MessageDao
import com.notgenius.summari3s.model.local.database.MessageDatabase
import com.notgenius.summari3s.model.repository.MessageRepository
import com.notgenius.summari3s.model.repository.MessageRepositoryImpl
import com.notgenius.summari3s.utils.NotificationUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideMessageDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        MessageDatabase::class.java,
        "message.db"
    ).build()

    @Provides
    fun provideNotificationUtil(
        @ApplicationContext
        context: Context
    ) = NotificationUtil(context)

    @Provides
    fun provideMessageDao(
        messageDatabase: MessageDatabase
    ) = messageDatabase.messageDao()

    @Provides
    fun provideMessageRepository(
        messageDao: MessageDao
    ): MessageRepository = MessageRepositoryImpl(
        messageDao
    )
}