package com.example.demovm

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

private const val TAG = "AlertDialogFragment"

class AlertDialogFragment constructor(context: Context) : DialogFragment() , InterfaceDialog {

    var confirmListener: InterfaceDialog.OnClickListener ?= null
    var mView: View
    var inflater: LayoutInflater
    init {
        Log.i(TAG, "AlertDialogFragment init: ")
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.mView = inflater.inflate(R.layout.alert_dialog, null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.i(TAG, "onCreateDialog: ")
        var builder = AlertDialog.Builder(context)
        builder.setView(mView)

        mView.findViewById<Button>(R.id.btn_alert_confirm).setOnClickListener {
            this.confirmListener?.onClick(  this )
        }

        return builder.create()
    }

    fun setTitle(title: String): AlertDialogFragment {
        this.mView.findViewById<TextView>(R.id.tv_alert_title).text = title
        return this
    }

    fun setContent(msg: String): AlertDialogFragment {
        this.mView.findViewById<TextView>(R.id.tv_alert_body).text = msg
        return this
    }

    fun setOnConfirmClickListener(listener: InterfaceDialog.OnClickListener): AlertDialogFragment{
        this.confirmListener = listener
        return this
    }

    override fun dismiss() {
        super.dismiss()
        Log.i(TAG, "dissmiss: ")
        super.onDestroy().apply { Log.i(TAG, "dismiss: destroy") }
    }



}

