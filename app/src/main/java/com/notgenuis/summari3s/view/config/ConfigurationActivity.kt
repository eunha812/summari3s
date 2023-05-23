package com.notgenuis.summari3s.view.config

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.notgenuis.summari3s.App
import com.notgenuis.summari3s.view.ui.theme.Summari3sTheme
import com.notgenuis.summari3s.view.ui.theme.backgroundColor1
import dagger.hilt.android.AndroidEntryPoint
import com.notgenuis.summari3s.view.config.views.ConfigurationScreen

@AndroidEntryPoint
class ConfigurationActivity : ComponentActivity() {
    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            Toast.makeText(this@ConfigurationActivity, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            Toast.makeText(this@ConfigurationActivity, "문자 요약 기능을 이용하려면 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("문자 요약 기능을 이용하려면 권한이 필요합니다.\n\n권한을 허용해주세요. [설정] > [권한]")
            .also {
                if(Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
                    it.setPermissions(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.RECEIVE_SMS)
                } else {
                    it.setPermissions(Manifest.permission.RECEIVE_SMS)
                }
            }
            .check()

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
}











