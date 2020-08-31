package com.example.demovm.mainFunctionName.accountinfo

import androidx.lifecycle.MutableLiveData
import com.example.demovm.base.BaseViewModel
import javax.inject.Inject

class AccountInfoViewModel @Inject constructor(): BaseViewModel(){

    var _headerTitle = MutableLiveData<String>().apply { value = "AccountInfo" }
    var headerTitle: MutableLiveData<String> = _headerTitle
    val headerBack = MutableLiveData<Boolean>().apply { value = true }
    var onBackClick = MutableLiveData<Void>()
    var rightPopupWindowOpen = MutableLiveData<Boolean>()

    var account = MutableLiveData<String>().apply { value = "Jack777" }
    var name = MutableLiveData<String>().apply { value = "Jack" }
    var email = MutableLiveData<String>().apply { value = "mary@gmail.com" }


    fun goBackClick(){
        onBackClick.value = null
    }
    fun rightPopupWindow_click(){
        rightPopupWindowOpen.value = null
    }

}