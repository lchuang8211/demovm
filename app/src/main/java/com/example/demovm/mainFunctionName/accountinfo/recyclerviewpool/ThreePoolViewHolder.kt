package com.example.demovm.mainFunctionName.accountinfo.recyclerviewpool

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.data.source.local.userinfo.UserInfoData
import com.example.demovm.databinding.LayoutRecyclerViewPoolBinding
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoFragmentViewModel

class ThreePoolViewHolder(val binding: LayoutRecyclerViewPoolBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        //透過Fragment(父元件)取得Binding的View元件
        fun from(parent: ViewGroup): ThreePoolViewHolder {
//            Log.i(TAG, "from: ")
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutRecyclerViewPoolBinding.inflate(layoutInflater, parent, false)
            return ThreePoolViewHolder(
                binding
            )
        }
    }

    //顯示資料的規則/邏輯
    fun bind(item: Int) {
        binding.txt.text = item.toString()
    }
}