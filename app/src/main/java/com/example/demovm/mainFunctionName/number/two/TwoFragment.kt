package com.example.demovm.mainFunctionName.number.two

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.demovm.databinding.TwoFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel

private const val TAG = "NormalFragment"
class TwoFragment : Fragment(){

    lateinit var binding : TwoFragmentBinding
    lateinit var activityViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TwoFragmentBinding.inflate(inflater,container,false)
        binding.activityViewModel = this.activityViewModel as FunctionNameMainActivityViewModel

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityViewModel = ViewModelProviders.of(this).get(FunctionNameMainActivityViewModel::class.java)
    }

}