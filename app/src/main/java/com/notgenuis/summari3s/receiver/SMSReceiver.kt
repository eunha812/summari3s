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

class MessageReceiver : BroadcastReceiver() {
    private val repository: MessageRepository = MessageRepositoryImpl()

    override fun onReceive(context: Context, intent: Intent) {
        if(!App.pref.isModeOn()) {
            return
        }

        if(intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            processSms(context, intent)
        } else if(intent.action == Telephony.Mms.Intents.){
            processMms()
        }
    }

    private fun processSms(context: Context, intent: Intent) {
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

                summariesMessage(context, messageFromAddress, messageBody)
            }
        }
    }

    private fun processMms() {

    }

    private fun summariesMessage(context: Context, address: String, message: String) {
        val noti = NotificationUtil(context)

        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getSummaries(message)

            if(result is ApiResult.Success) {
                noti.createNotification(address, result.data, 1)
                repository.save(address, message, result.data)
            } else {
                repository.save(address, message, null)
            }
        }
    }
}