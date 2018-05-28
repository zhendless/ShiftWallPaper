package com.zhendless.shiftwallpaper

import android.os.Environment
import java.io.File

object Constant {
    private val BASE_DIRECTORY = (Environment.getExternalStorageDirectory().path
            + File.separator + "com.zhendless.shiftwallpapaer" + File.separator)

    val VIDEO_DIRECTORY = BASE_DIRECTORY + "videos" + File.separator
}