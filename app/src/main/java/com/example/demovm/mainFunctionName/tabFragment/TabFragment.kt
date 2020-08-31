package com.example.demovm.mainFunctionName.tabFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demovm.R
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.TabFragmentBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel
import com.example.demovm.mainFunctionName.tabFragment.item.ItemDetail
import com.example.demovm.mainFunctionName.tabFragment.item.ItemAdapter

private const val TAG = "TabFragment"
class TabFragment: BaseDaggerFragment(){

    // ??????
    companion object{
        const val TAB_KIND = "TAB_KIND"
        fun newInstance(kind: Int): TabFragment {
            var frg = TabFragment()
            frg.apply {
                arguments =Bundle().apply {
                    putInt(TAB_KIND,kind)
                }
            }
            return frg
        }
    }

    override val viewModel by viewModels<TabFragmentViewModel>{ viewModelFactory}
    val activityViewModel by activityViewModels<FunctionNameMainActivityViewModel>{ viewModelFactory }
    override lateinit var binding : TabFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TabFragmentBinding.inflate(inflater,null,false).apply {
            this.viewModel = this@TabFragment.viewModel
            this.activityViewModel = this@TabFragment.activityViewModel
        }

        initHeader()
        initobserver()
        binding.rvItemList.layoutManager = GridLayoutManager(context, 4)
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    private fun initHeader() {
        activityViewModel.headerTitle.value = "Activity"
        activityViewModel.headerBack.value = false
        Log.i(TAG, "ViewPager headerBack: "+activityViewModel.headerBack.value )
    }

    private fun initobserver() {
        viewModel.itemListEvent.observe(this, Observer {
            Log.i(TAG, "initobserver: "+ viewModel.itemList.size)
            binding.rvItemList.adapter = ItemAdapter(viewModel).apply {
                var type = arguments?.getInt(TAB_KIND)
                var newlist: MutableList<ItemDetail> = arrayListOf()
                when(type){
                    1 -> {
                        for ( i in viewModel.itemList){
                            if(i.kind == type) newlist.add(i)
                        }
                    }
                    2 -> {
                        for ( i in viewModel.itemList){
                            if(i.kind == type) newlist.add(i)
                        }
                    }
                    else -> {
                        newlist.addAll(viewModel.itemList)
                        viewModel.tabKind.value = true
                    }
                }
                submit(newlist)
            }
        })

        viewModel.itemDetail.observe(viewLifecycleOwner, Observer {
            // ? 的用意
            it?.apply {
                Log.i(TAG, "initobserver: itemDetail " + it.name)
                activityViewModel.headerTitle.value = "from ViewPager " + it.name

                val navController = findNavController()
                when(it.name){
                    "one"->{
                        navController.navigate(R.id.one_fragment)
                        activityViewModel.headerBack.value = true
                        Log.i(TAG, "initobserver headerBack: " + activityViewModel.headerBack.value)
                    }
                    "two"->{
                        navController.navigate(R.id.two_fragment)
                        activityViewModel.headerBack.value = true
                        Log.i(TAG, "initobserver headerBack: " + activityViewModel.headerBack.value)
                    }
                }
            }
        })

    }


}