package com.notgenius.summari3s.view.config

import android.Manifest
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.notgenius.summari3s.App
import com.notgenius.summari3s.service.MessageNotificationListenerService
import com.notgenius.summari3s.view.ui.theme.Summari3sTheme
import com.notgenius.summari3s.view.ui.theme.backgroundColor1
import dagger.hilt.android.AndroidEntryPoint
import com.notgenius.summari3s.view.config.views.ConfigurationScreen

@AndroidEntryPoint
class ConfigurationActivity : ComponentActivity() {
    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            showPermissionDialog()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            Toast.makeText(this@ConfigurationActivity, "문자 요약 결과 알림을 수신하려면 알림 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            showPermissionDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()

        setContent {
            Summari3sTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor1
                ) {
                    ConfigurationScreen(
                        App.pref.isModeOn()
                    )
                }
            }
        }
    }

    private fun checkPermission() {
        if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
            TedPermission.create().setPermissionListener(permissionListener)
                .setDeniedMessage("문자 요약 결과 알림을 수신하려면 알림 권한이 필요합니다.\n\n권한을 허용해주세요. [설정] > [권한]")
                .setPermissions(Manifest.permission.POST_NOTIFICATIONS).check()
        } else {
            showPermissionDialog()
        }
    }

    private fun showPermissionDialog() {
        if(!isNotificationServiceListenerPermissionGranted()) {
            AlertDialog.Builder(this)
                .setTitle("권한 요청")
                .setMessage("요약 기능을 사용하려면 알림 접근 권한이 필요합니다.")
                .setPositiveButton("이동") { _,  _ ->
                    startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
                }
                .setNegativeButton("취소") { _,  _ ->
                    finish()
                }
                .create()
                .show()
        }
    }

    private fun isNotificationServiceListenerPermissionGranted(): Boolean {
        val componentName = ComponentName(this, MessageNotificationListenerService::class.java)
        val enabledListeners =
            Settings.Secure.getString(contentResolver, "enabled_notification_listeners")

        if (enabledListeners.isEmpty()) return false

        return enabledListeners.split(":").map {
            ComponentName.unflattenFromString(it)
        }.any {cn->
            cn == componentName
        }
    }
}











