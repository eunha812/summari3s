package com.notgenius.summari3s.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.notgenius.summari3s.R
import com.notgenius.summari3s.view.history.HistoryActivity

class NotificationUtil(private val context: Context) {
    companion object {
        const val FILE_NAME = "notification_count"
        const val COUNT = "count"

        const val ID = "id"
        const val SENDER = "sender"
        const val MESSAGE = "message"

        const val CHANNEL_ID = "summari3s_channel"
        const val CHANNEL_NAME = "메시지 요약 알림"
        const val CHANNEL_DESCRIPTION = "메시지를 요약한 결과 알림을 받습니다."
    }

    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    private fun createNotificationId(): Int {
        val count = prefs.getInt(COUNT, 0)
        prefs.edit().putInt(COUNT, count + 1).apply()
        return count + 1
    }

    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).also {
            it.description = CHANNEL_DESCRIPTION
        }

        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(sender: String, message: String, id: Long) {
        if(notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            createNotificationChannel()
        }

        val intent = Intent(context, HistoryActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.putExtra(ID, id)
            it.putExtra(SENDER, sender)
            it.putExtra(MESSAGE, message)
        }

        val notificationId = createNotificationId()

        val pendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(sender)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(notificationId, builder.build())
    }
}