package com.zhendless.shiftwallpaper.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.zhendless.shiftwallpaper.activity.LockScreenActivity


class LockScreenService : Service() {

    private val TAG = LockScreenService::class.java.simpleName
    private val mLockScreenBinder: LockScreenBinder = LockScreenBinder()

    private val mLockScreenReceiver: LockScreenReceiver = LockScreenReceiver()

    override fun onBind(intent: Intent?): IBinder {
        if (null != intent) {
        }
        return mLockScreenBinder
    }

    override fun onCreate() {
        super.onCreate()
        val filter: IntentFilter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(mLockScreenReceiver, filter)
    }

    inner class LockScreenBinder : Binder() {

    }

    inner class LockScreenReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {

            if (intent != null && intent.action == Intent.ACTION_SCREEN_OFF) {
                Log.d(TAG, "received action which action_screen_off!")
                val lockScreen = Intent(this@LockScreenService, LockScreenActivity::class.java)
                lockScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(lockScreen)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mLockScreenReceiver)
    }
}