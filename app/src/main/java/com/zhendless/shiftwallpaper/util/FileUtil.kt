package com.zhendless.shiftwallpaper.util

import android.content.Context
import java.io.*

object FileUtil {

    fun copyAssetsFileToSD(ctx: Context, targetFileName: String, outFileName: String) {
        var myInput: InputStream? = null
        var myOutput: OutputStream? = null
        try {
            myOutput = FileOutputStream(outFileName)
            myInput = ctx.assets.open(targetFileName)
            val buffer = ByteArray(1024)
            var length = myInput!!.read(buffer)
            while (length > 0) {
                myOutput.write(buffer, 0, length)
                length = myInput.read(buffer)
            }
            myOutput.flush()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (myInput != null) {
                try {
                    myInput.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            if (myOutput != null) {
                try {
                    myOutput.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }
}