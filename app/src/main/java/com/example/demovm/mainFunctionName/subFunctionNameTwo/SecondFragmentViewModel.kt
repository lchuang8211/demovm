package com.example.demovm.mainFunctionName.subFunctionNameTwo

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demovm.AlertDialogFragment
import com.example.demovm.AppUtils
import com.example.demovm.Event
import com.example.demovm.base.BaseViewModel
import com.example.demovm.base.BottomDrawBaseViewModel
import com.example.demovm.data.source.local.RoomDataBase
import com.example.demovm.data.source.local.userinfo.UserInfoData
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "SecondFragmentViewModel"
class SecondFragmentViewModel @Inject constructor(private val RoomDB: RoomDataBase): BottomDrawBaseViewModel(){

    var alertDialog = MutableLiveData<Boolean>()
    var alertDialogEvent = MutableLiveData<Event<Boolean>>().apply { Event( false) }
    fun btnAlertClick(){
        Log.i(TAG, "btnAlertClick: alert test ")
        /**
         *  alertDialog 監聽呼叫彈跳窗
         * */
        alertDialog.value = null
        alertDialogEvent.value = Event(true)
    }

}