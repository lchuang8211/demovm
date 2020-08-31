package com.example.demovm.mainFunctionName.tabFragment.item


import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.mainFunctionName.tabFragment.TabFragmentViewModel


class ItemAdapter(val viewModel: TabFragmentViewModel) : RecyclerView.Adapter<ItemViewHolder>(){
    var data = emptyList<ItemDetail>()

    fun submit(item: List<ItemDetail>){
        this.data = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position],viewModel)
    }


}

