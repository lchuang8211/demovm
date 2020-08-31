package com.example.demovm.mainFunctionName.subFunctionNameTwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.SecondFragmentBinding

class SecondFragment :BaseDaggerFragment(){

    override val viewModel by viewModels<SecondFragmentViewModel> { viewModelFactory }
    override lateinit var binding: SecondFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = SecondFragmentBinding.inflate(inflater,null,false).apply {
            this.viewModel = this@SecondFragment.viewModel
        }

        return binding.root
    }

}