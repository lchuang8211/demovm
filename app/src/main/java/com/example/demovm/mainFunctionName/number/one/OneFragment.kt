package com.example.demovm.mainFunctionName.number.one

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.demovm.databinding.OneFragmentBinding

import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel

class OneFragment : Fragment(){
    lateinit var binding : OneFragmentBinding
    lateinit var activityViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = OneFragmentBinding.inflate(inflater,container,false)
        binding.activityViewModel = this.activityViewModel as FunctionNameMainActivityViewModel

        initHeaderTitle()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityViewModel = ViewModelProviders.of(this).get(FunctionNameMainActivityViewModel::class.java)
    }

    private fun initHeaderTitle() {

    }
}