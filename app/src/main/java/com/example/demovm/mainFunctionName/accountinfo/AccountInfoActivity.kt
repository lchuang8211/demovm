package com.example.demovm.mainFunctionName.accountinfo

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.constraintlayout.widget.Constraints
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.demovm.R
import com.example.demovm.base.BaseActivity
import com.example.demovm.databinding.AccountInfoActivityBinding
import com.example.demovm.databinding.AccountInfoRightPopupWindowBinding
import com.example.demovm.databinding.RightPopupWindowBinding

private const val TAG = "AccountInfoActivity"
class AccountInfoActivity : BaseActivity(){

    override val viewModel by viewModels<AccountInfoViewModel>{viewModelFactory}

    override lateinit var binding: AccountInfoActivityBinding


    lateinit var rightBinding: AccountInfoRightPopupWindowBinding
    lateinit var rightPopupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.account_info_activity)
        binding.viewModel = this@AccountInfoActivity.viewModel
        binding.lifecycleOwner = this
        initTabViewPager()
        initHeader()
        initRightLayout()
    }

    private fun initHeader() {
        viewModel.headerTitle.observe(this, Observer {
            Log.i(TAG, "initHeader: "+ it)
            binding.accountInfoHeader.txtHeaderTitle.text = it
        })
        viewModel.onBackClick.observe(this, Observer {
            onBackPressed()
        })
        viewModel.rightPopupWindowOpen.observe(this, Observer {
            rightPopupWindow.showAtLocation(
                binding.accountInfoHeader.ivHeaderRightIcon,
                Gravity.TOP or Gravity.RIGHT,
                0,
                binding.accountInfoHeader.layoutHeaderBar.height
            )
        })
    }

    private fun initTabViewPager() {
        val tablist = arrayListOf(
            getString(R.string.accountinfo),
            "一般Fragment"
        )

        val adapter = AccountInfoViewPagerAdapter(
            this,
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        adapter.setTitle(tablist)
        binding.vpAccountinfo.adapter = adapter
        binding.tabAccountinfo.setupWithViewPager(binding.vpAccountinfo)
    }


    private fun initRightLayout() {

        /** 懸浮框 PopupWindow
         * 1.建立 Layout 的 xml
         * 2.Data Binding with view/layout
         * 3.Binding ViewModel
         * 4.把 Layout 丟給 PopupWindow
         * 5.設定 PopupWindow 相關設定
         * */

        rightBinding = AccountInfoRightPopupWindowBinding.inflate(LayoutInflater.from(this),null,false)
        rightBinding.viewModel = viewModel

        rightPopupWindow = PopupWindow(
            rightBinding.root,
            Constraints.LayoutParams.WRAP_CONTENT,
            Constraints.LayoutParams.WRAP_CONTENT,
            true
        )
        rightPopupWindow.isOutsideTouchable = true
//        rightPopupWindow.animationStyle =

    }

}