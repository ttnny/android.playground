package com.example.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MainActivity : AppCompatActivity() {

    private val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    private val mReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        this.registerReceiver(mReceiver, filter)

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }

    override fun onDestroy() {
        this.unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
        super.onDestroy()
    }

}