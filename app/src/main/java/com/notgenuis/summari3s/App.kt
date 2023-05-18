package com.notgenuis.summari3s

import android.app.Application
import com.notgenuis.summari3s.utils.PreferenceUtil

class App : Application() {
    companion object {
        lateinit var pref: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        pref = PreferenceUtil(this)
    }
}