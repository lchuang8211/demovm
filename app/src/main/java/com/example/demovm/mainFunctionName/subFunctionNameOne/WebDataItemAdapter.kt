package com.example.demovm.mainFunctionName.subFunctionNameOne

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.data.source.local.userinfo.UserInfoData
import com.example.demovm.databinding.WebDataItemBinding

private const val TAG = "WebDataItemAdapter"
class WebDataItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList= emptyList<UserInfoData>()
    private lateinit var viewModel: MainFragmentViewModel

    fun setViewModel(viewModel: MainFragmentViewModel) {
        this.viewModel = viewModel
        Log.i(TAG, "setViewModel: ")
    }

    fun sumbit(item: List<UserInfoData>){
        itemList = item
        Log.i(TAG, "sumbit: ")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i(TAG, "onCreateViewHolder: ")
        return ViewHolder.from( parent )
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "getItemCount: " + (itemList.size))
        return itemList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: ")

        //多型應用 對應相應Class取得對應資料
        if (holder is ViewHolder){
            holder.bind( itemList[ position ] )
            Log.i(TAG, "onBindViewHolder: position: "+position)
        }
    }

    class ViewHolder(val binding: WebDataItemBinding): RecyclerView.ViewHolder(binding.root){
        companion object{

            //透過Fragment(父元件)取得Binding的View元件
            fun from(parent: ViewGroup): ViewHolder {
                Log.i(TAG, "from: ")
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WebDataItemBinding.inflate(layoutInflater, parent,false)
                return ViewHolder(
                    binding
                )
            }
        }

        //顯示資料的規則/邏輯
        fun bind(item: UserInfoData){
            binding.txtUid.text = item.uid
            binding.txtName.text = item.name
            binding.txtNumber.text = item.number.toString()
        }
    }
}

