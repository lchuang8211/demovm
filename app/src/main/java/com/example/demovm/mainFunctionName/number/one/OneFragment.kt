package com.example.demovm.mainFunctionName.number.one

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.demovm.EventObserver
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.OneFragmentBinding

import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel
import timber.log.Timber

class OneFragment : BaseDaggerFragment(){
    override lateinit var binding : OneFragmentBinding
    lateinit var activityViewModel: ViewModel
    override val viewModel by viewModels<OneFragmentViewModel> { viewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = OneFragmentBinding.inflate(inflater,container,false)
        binding.activityViewModel = this.activityViewModel as FunctionNameMainActivityViewModel
        binding.viewModel = this@OneFragment.viewModel
        initHeaderTitle()
        init()
        intitDraw()
        return binding.root
    }

    private fun init() {

//        Timber.i("畫框 ${viewModel.drawHeight.value}:${binding.ivDrawable.height}+ / +${viewModel.drawWidth.value}:${binding.ivDrawable.width}")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun intitDraw(){
        binding.ivDrawable.setOnTouchListener(View.OnTouchListener { view: View, motionEvent: MotionEvent ->
            viewModel.drawHeight.value = binding.ivDrawable.height.toFloat()
            viewModel.drawWidth.value = binding.ivDrawable.width.toFloat()
            viewModel.drawClickPosition_X.value = motionEvent.getX()
            viewModel.drawClickPosition_Y.value = motionEvent.getY()
            Timber.i("畫框2 ${viewModel.drawHeight.value}:${binding.ivDrawable.height}+ / +${viewModel.drawWidth.value}:${binding.ivDrawable.width}")
//            Timber.i("畫框 ${viewModel.drawHeight.value}+ / +${viewModel.drawWidth.value}")
//            Timber.i("點擊 ${motionEvent.getX()}+ / +${motionEvent.getY()}")
            viewModel.drawClickEvent()
            return@OnTouchListener false
        })

//        binding.ivDrawable.setOnClickListener(View.OnClickListener {
//            viewModel.drawPoint()
//            Timber.i("click ")
//        })

        viewModel.bitmapEvent.observe(this,EventObserver{
            binding.ivDrawable.setImageBitmap(it)
        })
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityViewModel = ViewModelProviders.of(this).get(FunctionNameMainActivityViewModel::class.java)
    }

    private fun initHeaderTitle() {

    }
}