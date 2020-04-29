package com.lloyd.quizapp.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.lloyd.quizapp.R
import com.lloyd.quizapp.interfaces.DialogListener
import kotlinx.android.synthetic.main.custom_instructions.*

class CustomDialog(context: Context, val listener: DialogListener) : Dialog(context),
    View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_instructions)
        initViewsAndListeners()
    }

    private fun initViewsAndListeners() {
        start_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.start_btn) {
            dismiss()
            listener.onDismiss()
        }
    }

}