package com.servify.remoterconstrol

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
    }
    

    private fun checkPermission() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> if (!Settings.canDrawOverlays(this)) {
                requestScreenOverlay()
            } else {
                createFloatingBackButton()
            }
            else -> createFloatingBackButton()
        }
    }

    private fun createFloatingBackButton() { 
        val iconServiceIntent = Intent(this@MainActivity, FloatingButtonService::class.java)
        startService(iconServiceIntent)
    }

    private fun requestScreenOverlay() {
        var intent: Intent? = null
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        }
      startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE -> if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission();
            } else {
                createFloatingBackButton()
            }
        }

    }
}
