package com.example.demovm.mainFunctionName.subFunctionNameTwo

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demovm.AlertDialogFragment
import com.example.demovm.AppUtils
import com.example.demovm.base.BaseViewModel
import javax.inject.Inject

private const val TAG = "SecondFragmentViewModel"
class SecondFragmentViewModel @Inject constructor(): BaseViewModel(){

    var alertDialog = MutableLiveData<Boolean>()
    fun btnAlertClick(){
        Log.i(TAG, "btnAlertClick: alert test ")
        /**
         *  alertDialog 監聽呼叫彈跳窗
         * */
        alertDialog.value = null
    }

}