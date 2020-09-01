package com.example.demovm.mainFunctionName.subFunctionNameOne

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.MainFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel

class MainFragment : BaseDaggerFragment(){

    // override 在 BaseDaggerFragment 的參數
    override val viewModel by viewModels<MainFragmentViewModel> { viewModelFactory }

    val activityViewModel by activityViewModels<FunctionNameMainActivityViewModel>{ viewModelFactory }

    override lateinit var binding: MainFragmentBinding


//    private lateinit var activityViewModel: SecondActivityViewModel
//    private lateinit var viewModel: MainFragmentViewModel


//    val viewModel by viewModels<MainFragmentViewModel> {ViewModelProviders.Fac}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
//        ).get(MainFragmentViewModel::class.java)

//        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
//        activityViewModel = ViewModelProviders.of(this).get(SecondActivityViewModel::class.java)
        binding = MainFragmentBinding.inflate(inflater,null,false).apply {
            this.viewModel = this@MainFragment.viewModel
        }

        initObserver()
        initial()

        return binding.root
    }

    private fun initial() {
        //元件初始化
//        binding.btnRoom.setOnClickListener (View.OnClickListener {
//            viewModel.RoomDemo()
//        })
//
//        binding.btnDag.setOnClickListener (View.OnClickListener {
//            viewModel.btnDag_click()
//        })
    }

    private fun initObserver() {
        viewModel.roomChange.observe(this, Observer { webdata ->
            WebDataItemAdapter().apply {
                val linearLayout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                binding.rvWebData.layoutManager = linearLayout
                binding.rvWebData.adapter = this

                this.setViewModel(viewModel)
                this.sumbit(webdata)
            }
            Log.i("DBDATA: ","${viewModel.roomChange.value}")
        })
        viewModel.txtDag.observe(this, Observer {
//            binding.txtDag.text = it
        })

    }

}