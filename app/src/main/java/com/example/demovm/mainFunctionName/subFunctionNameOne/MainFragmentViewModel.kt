package com.example.demovm.mainFunctionName.subFunctionNameOne

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.demovm.AsyncTaskHttpDemo
import com.example.demovm.base.BaseViewModel
import com.example.demovm.dagger.ApiModule
//import com.example.demovm.dagger.DaggerApplicationComponent
import com.example.demovm.data.source.local.RoomDataBase
import com.example.demovm.data.source.local.userinfo.UserInfoData
import com.example.demovm.domain.fruit.AppleUseCase
import com.example.demovm.data.api.WebApi
import com.example.demovm.data.api.WebReponse
import com.example.demovm.data.api.WebRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val TAG = "MainFragmentViewModel"

class MainFragmentViewModel @Inject constructor(
    private var appleUseCase: AppleUseCase,
    private val RoomDB: RoomDataBase,
    private val connectApi: WebApi
): BaseViewModel() {

// private var RoomDB : RoomDataBase
//    @Inject constructor()
//    @Inject constructor(private var appleUseCase: AppleUseCase)
//    var RoomDB = RoomDataBase.getInstance(Application())

    //使用 AsyncTask 連線 Http
    fun AsyncTask(): String{
        val obEdtxt = ObservableField<String>().apply { "Input" }
        val AsyncFinish = AsyncTaskHttpDemo().execute(obEdtxt).get().get()!!
        return AsyncFinish
    }

    var textName = MutableLiveData<String>()

    var alistWebReponse = MutableLiveData<ArrayList<WebReponse>>()
    var roomChange = MutableLiveData<List<UserInfoData>>()

    var mURL = "http://10.0.2.2/"  //132.136
    //建立 Retrofit 的實體
    val retrofitInstance = Retrofit.Builder()
        .baseUrl(mURL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOKHttpClient())
        .build()
        .create(WebApi::class.java)

    //建立 OKhttp 的條件
    fun getOKHttpClient(): OkHttpClient {
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(ApiModule.TempInterceptor())
            .connectTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .build()
    }

    //RxKotlin + Http
    fun Request(){
        Log.i(TAG, "Request: ")
        retrofitInstance.postData(WebRequest(10)) //{"uid":"888"}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    var j: Int =0
                   for (i in it){
                       ///// 加入 Arraylist
                       alistWebReponse.postValue(it)
//                       alistWebReponse.apply { value = it }
//                       Log.i(TAG, "WebReponse: " + i.uid +" "+i.name+" "+i.number)
                       Log.i(TAG, "Request: it: "+it.get(j))
                       j++
                   }
                    Log.i(TAG, "Request: alwb:" + alistWebReponse.value)
                    //Log.i(TAG, "getWebData: uid: "+it.uid +" name: "+it.name+" number: "+it.number)
                },{
                    throw it
                },{
                    Log.i(TAG, "getWebData: Complete ")
                }
            )
            .apply {
                CompositeDisposable().add(this)
            }
    }

    /**
     *  RxKotlin + Retrofit + RoomDataBase + RecyclerView
     *  使用 Dagger 初始化相關連線資訊 UseCase RoomDataBase Retrofit
     *  將取得的資料透過 RoomDataBase 操作 Dao 儲存進 Local 的 SQLite 之中
     *  並放進 RecyclerView 的 Adapter 之中
     *  同時透過 Observer 改變 RecyclerView 的資訊
     */
    fun RoomDemo(){
        Log.i(TAG, "RoomDemo: ")
//        getDataUseCase.postData(WebRequest(10))
        try {
            connectApi.postData( WebRequest(10) )
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(
                {

                        Log.i(TAG, "RoomDemo: doOnNext room: ")
                        val array = arrayListOf<UserInfoData>()
                        it.forEach {
                            Log.i(TAG, "Request: it: " + it.uid + "/" + it.name + "/" + it.number)
                            val temp =
                                UserInfoData(
                                    it.uid,
                                    it.name,
                                    it.number
                                )
                            array.add(temp).apply { Log.i(TAG, "RoomDemo: addtemp: " + temp) }
                        }

                        RoomDB.webData().insertWebData(array)
//                    RoomDB.provideRoomDataBase(this).webData().insertWebData(array)
                        roomChange.postValue(array)

                },{
                    throw it
                },{
                    Log.i(TAG, "getWebData: Complete ")
                }
            ).apply {
                    compositeDisposable.add(this)
            }
        }catch (e: Exception){
            Log.i(TAG, "RoomDemo: "+ e.message)
        }
    }

    //Dagger 建立 UseCase 並取得資料，透過 Observer 改變 View 的資訊
    var txtDag = MutableLiveData<String>()
    fun btnDag_click() {
        Log.i(TAG, "btnDag03_click: ")
        txtDag.value = appleUseCase.doSometing()
    }

}

