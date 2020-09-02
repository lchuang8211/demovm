package com.example.demovm.mainFunctionName

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.constraintlayout.widget.Constraints
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.R
import com.example.demovm.base.BaseActivity
import com.example.demovm.databinding.FunctionNameMainActivityBinding
import com.example.demovm.databinding.RightPopupWindowBinding
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoActivity
import com.example.demovm.mainFunctionName.textBanner.TextBannerAdapter

import javax.inject.Inject


private const val TAG = "MainActivity"

class FunctionNameMainActivity @Inject constructor() :
    BaseActivity() {   // AppCompatActivity()   //BaseActivity()


    // 透用過 ViewModel Map 來綁定 View 和 ViewModel
    override val viewModel by viewModels<FunctionNameMainActivityViewModel> { viewModelFactory }

    // 不透過 Inject 方式提供 ViewModel
//    private lateinit var NormalviewModel: FunctionNameMainActivityViewModel

    // Binding 格式 ＝ xml 的 class 名稱加上 Binding
    override lateinit var binding: FunctionNameMainActivityBinding

    lateinit var rightBinding: RightPopupWindowBinding
    lateinit var rightPopupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // 不透過 Inject 方式提供 ViewModel
//        NormalviewModel = ViewModelProviders.of(this).get(FunctionNameMainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.function_name_main_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObserver()
        initWhereToGo()
        initRightLayout()
        initTextBanner()
        initGoTop()
    }

    private fun initGoTop() {
//        binding.goToTop.setOnClickListener { View.OnClickListener { } }
    }

    private fun initTextBanner() {
        var tvBannerList = arrayListOf<String>()
        tvBannerList.add("1111")
        tvBannerList.add("跑跑跑")
        tvBannerList.add("run run run")
        binding.tvBanner.setDatas(tvBannerList as List<String>)

        val adapter = TextBannerAdapter(viewModel).apply { this.submit(tvBannerList) }
        binding.rvBanner.adapter = adapter
        binding.rvBanner.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvBanner.start()

    }


    private fun initObserver() {

        var navController = Navigation.findNavController(this, R.id.frg_under_activity)
        var navGraph = navController.navInflater.inflate(R.navigation.hhhnavigation)
        navGraph.startDestination = R.id.host_fragment
        navController.setGraph(navGraph)

        viewModel.destinationC.observe(this, Observer {
            Log.i(TAG, "initial: frg12")
            when (it) {
                "frg2" -> {
                    navController.navigate(R.id.action_destination_frg2_to_destination_frg1)
                }
                "frg1" -> {
                    navController.navigate(R.id.action_destination_frg1_to_destination_frg2)
                }
            }
        })

        // 返回鍵
        viewModel.onBackClick.observe(this, Observer {
            onBackPressed()
        })

        // 右邊 Menu 懸浮視窗
        viewModel.rightPopupWindowOpen.observe(this, Observer {
            rightPopupWindow.showAtLocation(
                binding.headerBar.ivHeaderRightIcon,
                Gravity.RIGHT or Gravity.TOP, 0, binding.headerBar.layoutHeaderBar.height
            )
        })

    }

    fun initWhereToGo() {
        // 設定跳轉 Activity 事件
        setupStartActivityEvent(viewModel.gotoAccountInfoEvent, AccountInfoActivity::class)
    }

    private fun initRightLayout() {
        /** 懸浮框 PopupWindow
         * 1.建立 Layout 的 xml
         * 2.DataBinding with view/layout
         * 3.Binding ViewModel
         * 4.把 Layout 丟給 PopupWindow
         * 5.設定 PopupWindow 相關設定
         * */

        rightBinding = RightPopupWindowBinding.inflate(LayoutInflater.from(this), null, false)
        rightBinding.viewModel = viewModel

        rightPopupWindow = PopupWindow(
            rightBinding.root,
            Constraints.LayoutParams.WRAP_CONTENT,
            Constraints.LayoutParams.WRAP_CONTENT,
            true
        )

        rightPopupWindow.isOutsideTouchable = true
    }

}

