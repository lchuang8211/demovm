package com.example.demovm.mainFunctionName.subFunctionNameTwo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demovm.AlertDialogFragment
import com.example.demovm.EventObserver
import com.example.demovm.InterfaceDialog
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.SecondFragmentBinding

private const val TAG = "SecondFragment"

class SecondFragment :BaseDaggerFragment() {



    override val viewModel by viewModels<SecondFragmentViewModel> { viewModelFactory }
    override lateinit var binding: SecondFragmentBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView: ")

        binding = SecondFragmentBinding.inflate(inflater,null,false).apply {
            this.viewModel = this@SecondFragment.viewModel
        }

        initObserver()

        return binding.root
    }

    fun initObserver() {
        /**
         * AlertDialog 視窗
         * setContent : 設定 body
         * 透過 FragmentManager 顯示(show) Fragment
         */
        viewModel.alertDialog.observe(this, Observer {

            Log.i(TAG, "onCreateView: alertDialog ")
            AlertDialogFragment( activity as Context )
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

        viewModel.alertDialogEvent.observe(this,EventObserver{
            Log.i(TAG, "onCreateView: alertDialogEvent ")
            AlertDialogFragment( activity as Context )
                .setTitle("EventObserver")
                .setContent("透過 Event 包裝 LiveData\n"
                        +"而Observer Event 本身，讓觀察者僅看到 Event 本身，而不會直接知道內部資料是否更新\n"
                        +"避免在載入資料/初始化時執行或重複執行 Observer 的資料")
                .setOnConfirmClickListener(object : InterfaceDialog.OnClickListener{
                    override fun onClick(it: InterfaceDialog) {
                        Log.i(TAG, "OnConfirmClick: ")
                        it.dismiss()
                    }
                })
                .show(fragmentManager!!,"")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }
}