package com.example.demovm

interface InterfaceDialog {
    // 自訂 click 的 function

    interface OnClickListener {
        fun onClick(interfaceDialog: InterfaceDialog)
    }

    interface OnClickListener1 {
        fun onClick()
    }

    fun dismiss()

}