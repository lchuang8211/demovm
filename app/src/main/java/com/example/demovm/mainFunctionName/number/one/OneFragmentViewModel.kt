package com.example.demovm.mainFunctionName.number.one

import android.graphics.*
import android.os.Build
import android.util.EventLog
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.demovm.AppUtils
import com.example.demovm.Event
import com.example.demovm.R
import com.example.demovm.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class OneFragmentViewModel @Inject constructor() : BaseViewModel() {

    var drawHeight = MutableLiveData<Float>()
    var drawWidth = MutableLiveData<Float>()
    var drawClickPosition_X = MutableLiveData<Float>()
    var drawClickPosition_Y = MutableLiveData<Float>()
    var drawClickEvent = MutableLiveData<Event<Boolean>>()
    var sideLength = 20
    var PaintTT = Array(sideLength) { BooleanArray(sideLength) { false } }

    @RequiresApi(Build.VERSION_CODES.M)
    fun drawClickEvent() {
        var click_X = drawClickPosition_X.value!! / drawWidth.value!!
        var click_Y = drawClickPosition_Y.value!! / drawHeight.value!!
        var percent_X: Int? = null
        var percent_Y: Int? = null
        for(i in 0..sideLength-1){
            if (click_X > i/sideLength.toFloat()){ percent_X = i }
            if (click_Y > i/sideLength.toFloat()){ percent_Y = i }
        }
        if (click_X < 0F || click_Y < 0F) return
//        Timber.d("比例 $click_X / $click_Y : $percent_X / $percent_Y")
        PaintTT[percent_X!!][percent_Y!!] = true
        Timber.i("1: ${PaintTT[percent_X!!][percent_Y!!]}")
        drawPoint()
    }

    lateinit var bitmap: Bitmap
    var color: Int = 0

    var bitmapEvent = MutableLiveData<Event<Bitmap>>()
    @RequiresApi(Build.VERSION_CODES.M)
    fun drawPoint() {

        bitmap = Bitmap.createBitmap(
            drawWidth.value!!.toInt(),
            drawHeight.value!!.toInt(),
            Bitmap.Config.ARGB_8888
        )
//        Timber.i("2: ${PaintTT[percent_X!!][percent_Y!!]}")
        var paint = Paint()

        paint.isAntiAlias = true
//        Timber.d("比例 : $percent_X / $percent_Y")

//        var rect = Rect()
//        rect.top = percent_Y * (drawHeight.value!! / 10)!!.toInt()
//        rect.bottom = (percent_Y + 1) * (drawHeight.value!! / 10)!!.toInt()
//        rect.left = percent_X * (drawHeight.value!! / 10)!!.toInt()
//        rect.right = (percent_Y + 1) * (drawHeight.value!! / 10)!!.toInt()

        for (i in 0..sideLength-1) {
            for (j in 0..sideLength-1) {
                var rect = Rect()
                if (PaintTT[i][j] == true)
                    color = AppUtils.getContext().getColor(R.color.teal)
                else
                    color = AppUtils.getContext().getColor(R.color.white)
                paint.setColor(color)
                rect.top = j * (drawHeight.value!! / sideLength)!!.toInt()
                rect.bottom = (j + 1) * (drawHeight.value!! / sideLength)!!.toInt()
                rect.left = i * (drawWidth.value!! / sideLength)!!.toInt()
                rect.right = (i + 1) * (drawWidth.value!! / sideLength)!!.toInt()
                var cavas = Canvas(bitmap)
                cavas.drawRect(rect, paint)
            }
        }
        bitmapEvent.value = Event(bitmap)
    }

}