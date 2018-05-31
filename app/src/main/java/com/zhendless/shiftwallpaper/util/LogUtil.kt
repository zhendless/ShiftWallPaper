package com.zhendless.shiftwallpaper.util

import android.util.Log
import com.zhendless.shiftwallpaper.BuildConfig

object LogUtil {

    fun print(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

}