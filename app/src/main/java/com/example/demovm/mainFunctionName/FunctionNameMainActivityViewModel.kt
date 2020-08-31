package com.example.demovm.mainFunctionName

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demovm.base.BaseViewModel
import javax.inject.Inject

private const val TAG = "FunctionNameMainActivit"
class FunctionNameMainActivityViewModel @Inject constructor(): BaseViewModel() {

    var headerTitle = MutableLiveData<String>().apply { value = "Activity" }
    val headerBack = MutableLiveData<Boolean>().apply { value = false }
    var onBackClick = MutableLiveData<Void>()
    var rightPopupWindowOpen = MutableLiveData<Boolean>()


    var destinationC = MutableLiveData<String>().apply { value = "" }
    var gotoAccountInfoEvent = MutableLiveData<Void>()
    fun btnFrg1_click(){
        Log.i(TAG, "btnFrg1_click: ")
        destinationC.value = "frg2"
    }
    fun btnFrg2_click(){
        Log.i(TAG, "btnFrg2_click: ")
        destinationC.value = "frg1"
    }
    fun gotoAccountInfo(){
        Log.i(TAG, "gotoAccountInfo: ")
        gotoAccountInfoEvent.value = null
    }

    fun goBackClick(){
        onBackClick.value = null
    }

    fun rightPopupWindow_click(){
        rightPopupWindowOpen.value = null
    }

}