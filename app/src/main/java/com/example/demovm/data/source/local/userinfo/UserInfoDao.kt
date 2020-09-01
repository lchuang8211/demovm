package com.example.demovm.data.source.local.userinfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

// 操作用

@Dao
interface UserInfoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWebData(list : List<UserInfoData>) // ?? WebReponse ??

    @Query("SELECT * FROM RoomDemo")
    fun getAll(): List<UserInfoData>

    @Query("SELECT * FROM RoomDemo WHERE name = :inputName ")
    fun getName(inputName: String): Observable<List<UserInfoData>>

    @Query("SELECT * FROM RoomDemo WHERE number = :inputNumber ")
    fun getNumber(inputNumber: String): List<UserInfoData>

}