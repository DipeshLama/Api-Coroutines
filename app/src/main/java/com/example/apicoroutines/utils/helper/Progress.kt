package com.example.apicoroutines.utils.helper

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ProgressBar
import com.example.apicoroutines.R

object Progress {
    fun showProgress(activity: Activity): Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(
            ColorDrawable(0))
        dialog.setContentView(R.layout.layout_dialog_progress)
        val progressBar: ProgressBar = dialog.findViewById(R.id.pgBar)
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }
}