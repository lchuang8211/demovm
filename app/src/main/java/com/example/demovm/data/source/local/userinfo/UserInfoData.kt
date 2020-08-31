package com.example.demovm.data.source.local.userinfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RoomDemo")
data class UserInfoData(
    @PrimaryKey
    var uid: String,

    //ColumnInfo = schema name 欄位名稱
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number")var number: Int

)