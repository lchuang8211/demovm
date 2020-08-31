package com.example.demovm.mainFunctionName

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.FunctionMainFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.function_main_fragment.*

class FunctionMainFragment : BaseDaggerFragment(){

    override val viewModel by viewModels<FunctionMainFragmentViewModel> { viewModelFactory }

    val activityViewModel by activityViewModels<FunctionNameMainActivityViewModel> { viewModelFactory }

    override lateinit var binding : FunctionMainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FunctionMainFragmentBinding.inflate(inflater, null, false).apply {
            this.viewModel = this@FunctionMainFragment.viewModel
        }

        initTab()
        return binding.root
    }

    private fun initTab() {
        var tabTitle = arrayListOf(
            "Tab1",
            "Tab2",
            "全部",
            "奇數",
            "偶數"
        )
        var adapter = FragmentViewPagerAdapter(
            this.context!!,
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        adapter.setTitle(tabTitle)
        binding.vpFrg.adapter = adapter
        binding.tabList.setupWithViewPager(binding.vpFrg)

//        val t1 = binding.tabList.newTab().setText("Tab1")
//        val t2 = binding.tabList.newTab().setText("Tab2")
//        val t3 = binding.tabList.newTab().setText("Tab3")
//        val t4 = binding.tabList.newTab().setText("Tab4")
//        val t5 = binding.tabList.newTab().setText("Tab5")
//        binding.tabList.addTab(t1,0)
//        binding.tabList.addTab(t2,1)
//        binding.tabList.addTab(t3,2)
//        binding.tabList.addTab(t4,3)
//        binding.tabList.addTab(t5,4)

    }
}