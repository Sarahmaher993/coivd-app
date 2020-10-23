package com.smaher.covmeter.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.smaher.covmeter.R

class ProgressDialog (
    context: Context?
) : AppCompatDialog(context) {
        init {
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.progress_dialog_view)
            setCancelable(false)
            window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
        }
    }
