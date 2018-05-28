package com.zhendless.shiftwallpaper.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.VideoView
import com.zhendless.shiftwallpaper.R
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import com.zhendless.shiftwallpaper.Constant
import com.zhendless.shiftwallpaper.util.FileUtil
import com.zhendless.shiftwallpaper.util.ToastUtil
import java.io.File


class MainActivity : BaseActivity(), View.OnTouchListener {

    private var videoView: VideoView? = null
    private var imageView: ImageView? = null
    private var isVideoStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        checkPermissionWriteExternalStorage()

    }

    override fun onWriteExternalStorageGranted() {
        super.onWriteExternalStorageGranted()
        initMediaPlayer()
    }

    override fun onWriteExternalStorageDenied() {
        super.onWriteExternalStorageDenied()
        ToastUtil.showLongToast(this, R.string.toast_permission_write_storage_is_needed)
    }

    private fun initMediaPlayer() {
        copyFile()
        val videoFilePath = Constant.VIDEO_DIRECTORY + "dragon_ball_goku_1.mp4"
        videoView = findViewById<VideoView>(R.id.video_view)
        videoView?.setVideoPath(videoFilePath)
        videoView?.setOnCompletionListener { finish() }
        videoView?.suspend()
        videoView?.setOnTouchListener(this)
        /**
         * MediaMetadataRetriever class provides a unified interface for retrieving
         * frame and meta data from an input media file.
         */
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(videoFilePath)

        val bitmap = mmr.frameAtTime//获取第一帧图片
        imageView = findViewById<ImageView>(R.id.image_view_first_frame)
        imageView?.setImageBitmap(bitmap)
        mmr.release()//释放资源
    }

    private fun copyFile() {
        val file = File(Constant.VIDEO_DIRECTORY)
        if (!file.exists()) {
            file.mkdirs()
        }
        val outFileName = Constant.VIDEO_DIRECTORY + "dragon_ball_goku_1.mp4"
        val targetFile = File(outFileName)
        if (targetFile.exists()) {
            targetFile.delete()
        }
        FileUtil.copyAssetsFileToSD(this, "dragon_ball_goku_1.mp4", outFileName)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (isVideoStarted) {
                    videoView?.resume()
                } else {
                    imageView?.visibility = View.INVISIBLE
                    videoView?.start()
                    isVideoStarted = true
                }
                true
            }

            MotionEvent.ACTION_UP -> {
                videoView?.suspend()
                true
            }

            else -> false
        }

    }
}
