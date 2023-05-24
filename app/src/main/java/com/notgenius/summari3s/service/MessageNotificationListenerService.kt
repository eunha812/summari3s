package com.notgenius.summari3s.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.notgenius.summari3s.App
import com.notgenius.summari3s.model.ApiResult
import com.notgenius.summari3s.model.repository.MessageRepository
import com.notgenius.summari3s.utils.NotificationUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MessageNotificationListenerService : NotificationListenerService() {
    @Inject
    lateinit var repository: MessageRepository
    @Inject
    lateinit var notificationUtil: NotificationUtil

    companion object {
        private val PACKAGE_NAMES = arrayOf(
            "com.samsung.android.messaging",
            "com.google.android.apps.messaging",
            "com.android.mms",
            "com.mi.global.bbs",
            "com.sonyericsson.conversations",
        )
        private const val TAG = "MMSNotificationListener_낫지니어스"
    }

    override fun onCreate() {
        super.onCreate()
        notificationUtil = NotificationUtil(this)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if(!App.pref.isModeOn()) {
            return
        }

        for (name in PACKAGE_NAMES) {
            if(sbn?.packageName == name) {
                val extras = sbn.notification.extras
                val title = extras.getString(Notification.EXTRA_TITLE)
                val text = extras.getString(Notification.EXTRA_TEXT)

                if(title == null || text == null) {
                    return
                }

                summariesMessage(title, text)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        for (name in PACKAGE_NAMES) {
            if(sbn?.packageName == name) {
                Log.d(TAG, "onNotificationRemoved: ")
            }
        }
    }

    private fun summariesMessage(address: String, message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.createSummary(address, message)
            val summary = result.first

            if(summary is ApiResult.Success) {
                notificationUtil.createNotification(address, summary.data, result.second)
            }
        }
    }
}