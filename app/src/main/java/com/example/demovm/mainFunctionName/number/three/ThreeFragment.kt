package com.example.demovm.mainFunctionName.number.three

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.demovm.databinding.ThreeFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel

class ThreeFragment :Fragment() {

    lateinit var binding : ThreeFragmentBinding
    lateinit var activityViewModel: ViewModel

    @SuppressLint("TimberArgCount")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ThreeFragmentBinding.inflate(inflater,container,false)
        binding.activityViewModel = this.activityViewModel as FunctionNameMainActivityViewModel

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityViewModel = ViewModelProviders.of(this).get(FunctionNameMainActivityViewModel::class.java)
    }

}