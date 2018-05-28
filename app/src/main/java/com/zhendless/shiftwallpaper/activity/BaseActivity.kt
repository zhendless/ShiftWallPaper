package com.zhendless.shiftwallpaper.activity

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.zhendless.shiftwallpaper.R

open class BaseActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 999
        private const val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 998
    }

    fun checkPermissionWriteExternalStorage() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (PackageManager.PERMISSION_GRANTED != permissionCheck) {
                requestPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        getString(R.string.is_permission_rationale_write_storage),
                        PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
            } else {
                onWriteExternalStorageGranted()
            }
        }
    }

    private fun requestPermission(activity: Activity, permission: String, rationale: String, requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            AlertDialog.Builder(activity)
                    .setTitle(R.string.dialog_tips_is_permission)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.dialog_button_ok, DialogInterface.OnClickListener { _, _ -> ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode) })
                    .setNegativeButton(R.string.dialog_button_cancel, null)
                    .create().show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onReadExternalStorageGranted()
                } else {
                    onReadExternalStorageDenied()
                }
            }
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onWriteExternalStorageGranted()
                } else {
                    onWriteExternalStorageDenied()
                }
            }
        }
    }

    open fun onReadExternalStorageGranted() {

    }

    open fun onReadExternalStorageDenied() {

    }

    open fun onWriteExternalStorageGranted() {

    }

    open fun onWriteExternalStorageDenied() {

    }

}