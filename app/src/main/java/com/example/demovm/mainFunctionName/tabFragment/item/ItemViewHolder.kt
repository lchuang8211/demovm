package com.example.demovm.mainFunctionName.tabFragment.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.AppUtils
import com.example.demovm.databinding.IconListItemBinding
import com.example.demovm.mainFunctionName.tabFragment.TabFragmentViewModel

private const val TAG = "ItemViewHolder"
class ItemViewHolder(val binding: IconListItemBinding): RecyclerView.ViewHolder(binding.root){

    companion object{
        //透過Fragment(父元件)取得Binding的View元件
        fun from(parent: ViewGroup): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = IconListItemBinding.inflate(layoutInflater, parent,false)
            return ItemViewHolder(binding)
        }
    }

    //顯示資料的規則/邏輯
    fun bind(item: ItemDetail, viewModel: TabFragmentViewModel){


        //將實體(item viewmodel)丟給 binding
        binding.item = item
        binding.viewModel = viewModel
        binding.tvItem.text = item.name

        //setBackgroundResource( R.XX.XXX ) R 之中的 ID
        //透過 getIdentifier 轉換位址取得 ID ( name(檔案名稱), type(類型 ex: color, drawable, ... ), package )
        //必須由 Context 去使用 getResources 下的 getIdentifier 方法，所以透過自訂的全域工具包(AppUtils)取得 Context
        binding.ivItem.setBackgroundResource(AppUtils.getResources().getIdentifier(item.imgName, "drawable", "com.example.demovm"))
    }

}
