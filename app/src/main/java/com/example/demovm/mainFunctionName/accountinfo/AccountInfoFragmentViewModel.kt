package com.example.demovm.mainFunctionName.accountinfo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demovm.base.BaseViewModel
import javax.inject.Inject

private const val TAG = "AccountInfoFragmentView"
class AccountInfoFragmentViewModel @Inject constructor() : BaseViewModel(){

    var editAccount = MutableLiveData<Boolean>().apply { value = false }
    var editName = MutableLiveData<Boolean>().apply { value = false }
    var editEmail = MutableLiveData<Boolean>().apply { value = false }

    fun onEditBtnClick(info: AccountInfoDetail){
        Log.i(TAG, "onEditBtnClick: "+ info.value)
        when(info.value){
            "account" -> {
                editAccount.value = true
                Log.i(TAG, "onEditBtnClick editAccount: " + editAccount.value)
            }
            "name" -> {
                editName.value = true
            }
            "email" -> {
                editEmail.value = true
            }
        }
    }
    fun onEditSubmitClick(info: AccountInfoDetail){
        when(info.value){
            "account" -> {
                editAccount.value = false
                Log.i(TAG, "onEditBtnClick editAccount: " + editAccount.value)
            }
            "name" -> {
                editName.value = false
            }
            "email" -> {
                editEmail.value = false
            }
        }
    }

}