package com.example.demovm.data.source.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demovm.data.source.local.userinfo.UserInfoData
import com.example.demovm.data.source.local.userinfo.UserInfoDao


private const val TAG = "RoomDataBase"

// 指定 DataBase 中定義資料表的 class ， ＝ DataBase 下所含的 Table
@Database(
    entities = [
        UserInfoData::class
    ],version = 1 ,exportSchema = false
)
abstract class RoomDataBase : RoomDatabase(){

    // 指定 Table 對應的操作 class Dao
    abstract fun webData(): UserInfoDao

    // singleton 避免重複多次實體化資料庫  // how to instance ?
//    companion object{
//
//        //Volatile 用來避免編譯器優化
//        @Volatile
//        private var INSTANCE: RoomDataBase ?= null
//
//        fun getInstance(application: Context) : RoomDataBase{
//
//            if (INSTANCE!=null)
//                return INSTANCE!!
//
//            INSTANCE = Room.databaseBuilder(application, RoomDataBase::class.java,"RoomDemo.db")
//                .build()
//            Log.i(TAG, "getInstance: " + INSTANCE)
//            return INSTANCE!!
//        }
////        fun getInstance(application: Application) = INSTANCE ?:
////                Room.databaseBuilder(application,mDataBase::class.java, "RoomDemo")
////                    .build()
////                    .also {
////                        INSTANCE = it
////                    }
//
//    }
}
