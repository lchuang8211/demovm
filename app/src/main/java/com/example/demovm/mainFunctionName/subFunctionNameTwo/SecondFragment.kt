package com.example.demovm.mainFunctionName.subFunctionNameTwo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.demovm.AlertDialogFragment
import com.example.demovm.InterfaceDialog
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.SecondFragmentBinding

private const val TAG = "SecondFragment"
class SecondFragment :BaseDaggerFragment() {

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

        /**
         * AlertDialog 視窗
         * setContent : 設定 body
         * 透過 FragmentManager 顯示(show) Fragment
         */
        viewModel.alertDialog.observe(this, Observer {
            AlertDialogFragment(activity as Context)
                .setTitle("Title")
                .setContent("iiiii")
                .setOnConfirmClickListener(object : InterfaceDialog.OnClickListener{
                    override fun onClick(it: InterfaceDialog) {
                        Log.i(TAG, "OnConfirmClick: ")
                        it.dismiss()
                    }
                })
                .show(fragmentManager!!,"")

//            var fragment = AlertDialogFragment(activity as Context,"ttt")
//            fragment
//                .setContent("iiiii")
//                // Interface … does not have constructors
//                .onClick(fragment)
//            fragment
//                .show(fragmentManager!!,"")
        })

        return binding.root
    }

}