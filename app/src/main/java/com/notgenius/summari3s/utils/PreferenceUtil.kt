package com.notgenius.summari3s.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.notgenius.summari3s.model.ModelType
import com.notgenius.summari3s.model.ModelType.Companion.toModelType
import com.notgenius.summari3s.model.SummaryStrength
import com.notgenius.summari3s.model.SummaryStrength.Companion.toSummaryStrength

class PreferenceUtil(context: Context) {
    companion object {
        const val FILE_NAME = "preferences"
        const val MODE = "mode"
        const val MODEL_TYPE = "modelType"
        const val SUMMARY_STRENGTH = "summaryStrength"
        const val SHOWED_ON_BOARDING = "showedOnBoarding"
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

    fun isModeOn() = prefs.getBoolean(MODE, false)

    fun toggleMode() {
        prefs.edit().putBoolean(MODE, !isModeOn()).apply()
    }

    fun getModelType() = prefs.getString(MODEL_TYPE, null)?.toModelType() ?: ModelType.CHAT_GPT

    fun setModelType(modelType: ModelType) {
        prefs.edit().putString(MODEL_TYPE, modelType.typeName).apply()
    }

    fun getStrength(): SummaryStrength {
        val strength = prefs.getInt(SUMMARY_STRENGTH, -1)
        return if(strength == -1) SummaryStrength.MAX else strength.toSummaryStrength()
    }

    fun setStrength(summaryStrength: SummaryStrength) {
        prefs.edit().putInt(SUMMARY_STRENGTH, summaryStrength.power).apply()
    }

    fun checkOnBoardingShowed() {
        prefs.edit().putBoolean(SHOWED_ON_BOARDING, true).apply()
    }

    fun isOnBoardingShowed() = prefs.getBoolean(SHOWED_ON_BOARDING, false)
}