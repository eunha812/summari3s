package com.notgenius.summari3s

import android.app.Application
import com.notgenius.summari3s.utils.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var pref: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        pref = PreferenceUtil(this)
    }
}