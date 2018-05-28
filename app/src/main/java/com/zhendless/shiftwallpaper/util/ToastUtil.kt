package com.zhendless.shiftwallpaper.util

import android.content.Context
import android.widget.Toast

object ToastUtil {

    fun showLongToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun showLongToast(context: Context, resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }
}