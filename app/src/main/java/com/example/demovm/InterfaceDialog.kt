package com.example.demovm

interface InterfaceDialog {

    interface OnClickListener {
        fun onClick(interfaceDialog: InterfaceDialog)
    }

    interface OnClickListener1 {
        fun onLongClick()
    }

    fun dismiss()

}