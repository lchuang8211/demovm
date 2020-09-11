package com.example.demovm.mainFunctionName.accountinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.base.BaseDaggerFragment
import com.example.demovm.databinding.AccountInfoFragmentBinding
import com.example.demovm.mainFunctionName.accountinfo.recyclerviewpool.OnePoolAdapter
import com.example.demovm.mainFunctionName.accountinfo.recyclerviewpool.OnePoolViewHolder
import com.example.demovm.mainFunctionName.accountinfo.recyclerviewpool.ThreePoolAdapter
import com.example.demovm.mainFunctionName.accountinfo.recyclerviewpool.TwoPoolAdapter
import kotlinx.android.synthetic.main.account_info_fragment.*

private const val TAG = "AccountInfoFragment"

class AccountInfoFragment() : BaseDaggerFragment() {

    val activityViewModel by activityViewModels<AccountInfoViewModel> { viewModelFactory }
    override val viewModel by viewModels<AccountInfoFragmentViewModel> { viewModelFactory }
    override lateinit var binding: AccountInfoFragmentBinding

    var oneList: ArrayList<Int> = ArrayList<Int>().apply {
        this.add(1)
        this.add(2)
        this.add(3)
        this.add(4)
        this.add(5)
        this.add(6)
        this.add(7)
    }
    var twoList: ArrayList<Int> = ArrayList<Int>().apply {
        this.add(2)
        this.add(1)
        this.add(2)
        this.add(1)
        this.add(2)
        this.add(1)
        this.add(2)
    }
    var threeList: ArrayList<Int> = ArrayList<Int>().apply {
        this.add(3)
        this.add(2)
        this.add(3)
        this.add(2)
        this.add(3)
        this.add(2)
        this.add(3)
        this.add(2)
        this.add(3)
        this.add(2)
        this.add(3)
        this.add(2)
        this.add(3)
        this.add(2)
        this.add(3)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AccountInfoFragmentBinding.inflate(inflater, container, false).apply {
            this.viewModel = this@AccountInfoFragment.viewModel
            this.activityViewModel = this@AccountInfoFragment.activityViewModel
        }

        initObserver()
        initRecyclerViewPool()
        initButton()
        return binding.root

    }


    private fun initObserver() {
        viewModel.editAccount.observe(this, Observer {
            if (!binding.etAccount.text.isNullOrEmpty())
                activityViewModel.account.value = binding.etAccount.text.toString()
        })
        viewModel.editName.observe(this, Observer {
            if (!binding.etName.text.isNullOrEmpty())
                activityViewModel.name.value = binding.etName.text.toString()
        })
        viewModel.editEmail.observe(this, Observer {
            if (!binding.etEmail.text.isNullOrEmpty())
                activityViewModel.email.value = binding.etEmail.text.toString()
        })
    }

    /** RecyclerViewPool 用法
     * 為了效能問題，將多個 Adapter 共用一個RecyclerView，所以建立一個Pool來共享
     * 1.建立 pool: RecyclerView.RecycledViewPool
     * 2.將建立的複數 Adapter 放進 pool 中 pool.putRecycledView( myAdapter.createViewHolder(@NonNull ViewGroup parent, int viewType) )
     * 3.將 pool 指給 RecyclerView
     * 4.在 XML 中設置 LayoutManager  app:LayoutManager = "LayoutManager"
     * 5.剩下的跟一般設置一樣 給 RecyclerView adapter / LayoutManager
     * */

    var rvPool = RecyclerView.RecycledViewPool()
    var onePoolAdapter = OnePoolAdapter().apply { this.sumbit(oneList) }
    var twoPoolAdapter = TwoPoolAdapter().apply { this.sumbit(twoList) }
    var threePoolAdapter = ThreePoolAdapter().apply { this.sumbit(threeList) }

    fun initRecyclerViewPool() {
        rvPool.putRecycledView(onePoolAdapter.createViewHolder(binding.rvPool, 0))
        rvPool.putRecycledView(twoPoolAdapter.createViewHolder(binding.rvPool, 0))
        rvPool.putRecycledView(threePoolAdapter.createViewHolder(binding.rvPool, 0))
        rvPool.setMaxRecycledViews(0, 3)  //設置最大暫存數量
        binding.rvPool.setRecycledViewPool(rvPool)
        binding.rvPool.layoutManager = GridLayoutManager(context, 3)
    }

    private fun initButton() {
        binding.btnP1.setOnClickListener(View.OnClickListener {
            binding.rvPool.adapter = onePoolAdapter
            binding.rvPool.layoutManager = GridLayoutManager(context, 3)
        })
        binding.btnP2.setOnClickListener(View.OnClickListener {
            binding.rvPool.adapter = twoPoolAdapter
            binding.rvPool.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        })
        binding.btnP3.setOnClickListener(View.OnClickListener {
            binding.rvPool.adapter = threePoolAdapter
        })
    }
}