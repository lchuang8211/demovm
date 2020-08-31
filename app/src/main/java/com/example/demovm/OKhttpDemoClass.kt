package com.example.demovm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.IOException

private const val TAG = "COKhttp"

//OKhttp 基本連線過程
class OKhttpDemoClass {

    val res = MutableLiveData<String>()
    
    //建立client (取得導行機台)
    val httpconnect = OkHttpClient().newBuilder().build()

    //設置連線資訊 (在導航機器上輸入目標位置，建立路線)
    val request = Request.Builder()
        .url("http://10.0.2.2/demoVM.php")  // 目的地連線網址 或 IP
        .build()

    //建立Call (呼叫司機，將有路線資訊的導航機器給司機 )
    val call: Call = httpconnect.newCall(request)

    //執行Call 連線到網址
    fun connect(): String{
        var result: String = ""
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //連線失敗
            }
            override fun onResponse(call: Call, response: Response) {
                //連線成功 透過 Response 取得資料
                result = response.body!!.string()
                Log.i(TAG, "onResponse: " + result)
            }
        })
        return result
    }

    fun rxConnect(): Observable<Response> {
        Log.i(TAG, "rxConnect: "+ Thread.currentThread().id.toString())
        return Observable.fromCallable( { call.execute() } )
    }

    fun RxKotlinHttpDemo(){
        OKhttpDemoClass().rxConnect()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i(TAG, "RxkotlinHttp: it: " + it)
                },{
                    throw it
                },{
                    println("complete")
                })
            .apply {
                CompositeDisposable().add(this)
            }
    }
}
