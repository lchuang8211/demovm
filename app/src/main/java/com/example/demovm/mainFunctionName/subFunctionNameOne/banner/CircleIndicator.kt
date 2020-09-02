package com.example.demovm.mainFunctionName.subFunctionNameOne.banner

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.youth.banner.indicator.BaseIndicator


/**
 * 圆形指示器
 * 如果想要大小一样，可以将选中和默认设置成同样大小
 */
class CircleIndicator @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    BaseIndicator(context, attrs, defStyleAttr) {
    private var mNormalRadius: Int
    private var mSelectedRadius: Int
    private var maxRadius = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val count = config.indicatorSize
        if (count <= 1) {
            return
        }
        var selectedWidth: Int = 26
        var normalWidth: Int = 20
        //未選則的其他半徑大小
        mNormalRadius = normalWidth/2 //config.normalWidth / 2
        //當前選擇的半徑大小
        mSelectedRadius = selectedWidth/2 //config.selectedWidth / 2
        //考虑当 选中和默认 的大小不一样的情况
        maxRadius = Math.max(mSelectedRadius, mNormalRadius)
        //间距*（总数-1）+选中宽度+默认宽度*（总数-1）
        val width =
            (count - 1) * ( config.indicatorSpace + 2 ) + selectedWidth + normalWidth * (count - 1)
//        (count - 1) * ( config.indicatorSpace + 2 ) + config.selectedWidth + config.normalWidth * (count - 1)

        //設定View的長寬
        setMeasuredDimension(
            width,
            Math.max(config.normalWidth, config.selectedWidth)+13
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val count = config.indicatorSize
        if (count <= 1) {
            return
        }
        var left = 0f
        for (i in 0 until count) {
            mPaint.color =
                if (config.currentPosition == i) config.selectedColor else config.normalColor
            val indicatorWidth =
                if (config.currentPosition == i) config.selectedWidth else config.normalWidth
            val radius =
                if (config.currentPosition == i) mSelectedRadius else mNormalRadius
            canvas.drawCircle(left + radius, maxRadius.toFloat(), radius.toFloat(), mPaint)
            left += indicatorWidth + config.indicatorSpace.toFloat() + 2
        }
        //        mPaint.setColor(config.getNormalColor());
//        for (int i = 0; i < count; i++) {
//            canvas.drawCircle(left + maxRadius, maxRadius, mNormalRadius, mPaint);
//            left += config.getNormalWidth() + config.getIndicatorSpace();
//        }
//        mPaint.setColor(config.getSelectedColor());
//        left = maxRadius + (config.getNormalWidth() + config.getIndicatorSpace()) * config.getCurrentPosition();
//        canvas.drawCircle(left, maxRadius, mSelectedRadius, mPaint);
    }

    init {
        mNormalRadius = config.normalWidth / 2
        mSelectedRadius = config.selectedWidth / 2
    }
}