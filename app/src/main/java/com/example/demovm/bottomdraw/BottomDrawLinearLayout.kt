package com.example.demovm.bottomdraw

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demovm.base.BottomDrawBaseViewModel
import com.example.demovm.databinding.LayoutBottomDraweBinding
import java.util.zip.Inflater

class BottomDrawLinearLayout  : LinearLayout {

    private lateinit var binding: LayoutBottomDraweBinding
    private lateinit var viewModel: BottomDrawBaseViewModel

    @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ):super (context, attrs, defStyleAttr)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = LayoutBottomDraweBinding.inflate(inflater, this, true)
    }

    fun setViewModel(viewModel: BottomDrawBaseViewModel){
        this.viewModel = viewModel
        binding.viewModel = viewModel
    }

    fun setViewPagerAdapter(fm: FragmentManager){
        var adapter = BottomDrawTabAdapter(
            fm,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        binding.vpBtottomDraw.adapter = adapter
        binding.tablayout.setupWithViewPager(binding.vpBtottomDraw)
    }
}