package com.notgenuis.summari3s.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.model.ApiResult
import com.notgenuis.summari3s.model.repository.MessageRepository
import com.notgenuis.summari3s.model.repository.MessageRepositoryImpl
import com.notgenuis.summari3s.utils.NotificationUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SMSReceiver : BroadcastReceiver() {
    private lateinit var repository: MessageRepository

    private lateinit var context: Context
    private lateinit var intent: Intent
    private lateinit var notificationUtil: NotificationUtil

    override fun onReceive(context: Context, intent: Intent) {
        if(!App.pref.isModeOn()) {
            return
        }

        if(intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            this.context = context
            this.intent = intent
            processSMS()
        }
    }

    private fun processSMS() {
        val pdus = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("pdus", Array<Any>::class.java)
        } else {
            intent.getSerializableExtra("pdus") as Array<*>
        }

        pdus?.let {
            for (pdu in pdus) {
                val sms = SmsMessage.createFromPdu(pdu as? ByteArray, intent.getStringExtra("format"))
                val messageBody = sms?.messageBody.toString()
                val messageFromAddress = sms?.displayOriginatingAddress.toString()

                summariesMessage(messageFromAddress, messageBody)
            }
        }
    }

    private fun summariesMessage(address: String, message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.createSummary(address, message)

            if(result is ApiResult.Success) {
                notificationUtil.createNotification(address, result.data, 1)
            }
        }
    }
}