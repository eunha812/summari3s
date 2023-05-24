package com.notgenuis.summari3s.utils

import android.content.Context
import androidx.annotation.RawRes

fun readRawTextFile(context: Context, @RawRes resId: Int): String {
    val ins = context.resources.openRawResource(resId)
    val byteArray = ins.readBytes()
    return String(byteArray)
}