package com.example.demovm.base

import android.util.Log
import androidx.lifecycle.MutableLiveData

private const val TAG = "BottomDrawBaseViewModel"
abstract class BottomDrawBaseViewModel : BaseViewModel() {

    var bottomOpenState = MutableLiveData<Boolean>().apply { value = false }

    //false = 關閉狀態 true = 開起狀態
    fun btnTopSwitchClick() {
        Log.i(TAG, "btnTopSwitchClick: ")
        bottomOpenState.value = !(bottomOpenState.value?.equals(true) ?: false)
    }

}