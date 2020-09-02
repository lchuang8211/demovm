package com.example.demovm.mainFunctionName.subFunctionNameOne.banner

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.Indicator


class BaseBannerIndicator @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr), Indicator {
    protected var config: IndicatorConfig
    protected var mPaint: Paint
    protected var offset = 0f
    override fun getIndicatorView(): View {
        if (config.isAttachToBanner) {
            val layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            when (config.gravity) {
                IndicatorConfig.Direction.LEFT -> layoutParams.gravity =
                    Gravity.BOTTOM or Gravity.START
                IndicatorConfig.Direction.CENTER -> layoutParams.gravity =
                    Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                IndicatorConfig.Direction.RIGHT -> layoutParams.gravity =
                    Gravity.BOTTOM or Gravity.END
            }
            layoutParams.leftMargin = config.margins.leftMargin
            layoutParams.rightMargin = config.margins.rightMargin
            layoutParams.topMargin = config.margins.topMargin
            layoutParams.bottomMargin = config.margins.bottomMargin
            setLayoutParams(layoutParams)
        }
        return this
    }

    override fun getIndicatorConfig(): IndicatorConfig {
        return config
    }

    override fun onPageChanged(count: Int, currentPosition: Int) {
        config.indicatorSize = count
        config.currentPosition = currentPosition
        requestLayout()
    }

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        offset = positionOffset
        invalidate()
    }

    override fun onPageSelected(position: Int) {
        config.currentPosition = position
        invalidate()
    }

    override fun onPageScrollStateChanged(state: Int) {}

    init {
        config = IndicatorConfig()
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = config.normalColor
    }
}