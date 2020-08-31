package com.example.demovm.mainFunctionName.accountinfo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.demovm.databinding.NormalFragmentBinding

private const val TAG = "NormalFragment"
class NormalFragment : Fragment(){

    lateinit var binding : NormalFragmentBinding
    lateinit var activityViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NormalFragmentBinding.inflate(inflater,container,false)
        binding.activityViewModel = this.activityViewModel as AccountInfoViewModel

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityViewModel = ViewModelProviders.of(this).get(AccountInfoViewModel::class.java)
    }


}