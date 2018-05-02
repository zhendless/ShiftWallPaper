package com.zhendless.shiftwallpaper

import android.app.Application
import android.content.Intent
import com.zhendless.shiftwallpaper.service.LockScreenService

class PaperApplication : Application() {

    private val instance = this@PaperApplication

    override fun onCreate() {
        super.onCreate()
        val intent = Intent(this@PaperApplication, LockScreenService::class.java)
        startService(intent)
    }

}