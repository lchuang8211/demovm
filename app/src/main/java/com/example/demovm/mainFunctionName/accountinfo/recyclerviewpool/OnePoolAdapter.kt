package com.example.demovm.mainFunctionName.accountinfo.recyclerviewpool

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.AppUtils
import com.example.demovm.data.source.local.userinfo.UserInfoData
import com.example.demovm.databinding.WebDataItemBinding
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoFragmentViewModel
import com.example.demovm.mainFunctionName.subFunctionNameTwo.SecondFragmentViewModel

private const val TAG = "WebDataItemAdapter"
class OnePoolAdapter : RecyclerView.Adapter<OnePoolViewHolder>() {

    private var itemList= emptyList<Int>()
    private lateinit var viewModel: AccountInfoFragmentViewModel

    fun setViewModel(viewModel: AccountInfoFragmentViewModel) {
        this.viewModel = viewModel
        Log.i(TAG, "setViewModel: ")
    }

    fun sumbit(item: List<Int>){
        itemList = item
        Log.i(TAG, "sumbit: ")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnePoolViewHolder {
        Log.i(TAG, "onCreateViewHolder: ")
        return OnePoolViewHolder.from( parent )
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "getItemCount: " + (itemList.size))
        return itemList.size
    }


    override fun onBindViewHolder(holder: OnePoolViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: ")
        //多型應用 對應相應Class取得對應資料
        if (holder is OnePoolViewHolder){
            holder.bind( itemList[ position ] )
            Log.i(TAG, "onBindViewHolder: position: "+ position)
        }
    }


}

