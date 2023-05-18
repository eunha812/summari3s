package com.notgenuis.summari3s.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage

class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val pdus = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("pdus", Array<Any>::class.java)
            } else {
                intent.getSerializableExtra("pdus") as Array<*>
            }

            pdus?.let {
                for (pdu in pdus) {
                    val sms = SmsMessage.createFromPdu(pdu as? ByteArray, intent.getStringExtra("format"))
                    val messageBody = sms?.messageBody
                    val messageFromAddress = sms?.displayOriginatingAddress
                }
            }
        }
    }
}