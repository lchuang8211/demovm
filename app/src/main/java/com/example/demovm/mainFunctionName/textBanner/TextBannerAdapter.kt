package com.example.demovm.mainFunctionName.textBanner

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel

class TextBannerAdapter(val viewModel: FunctionNameMainActivityViewModel) :
    ListAdapter<String, TextBannerViewHolder>(BannerDiffCallBack()) {

    var data = emptyList<String>()
    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
        // Int.MAX_VALUE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextBannerViewHolder {
        return TextBannerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextBannerViewHolder, position: Int) {
        if (data.size > 0) {
            holder.bind(data[position % data.size], viewModel)
        }
    }

    fun submit(list: List<String>) {
        this.data = list as ArrayList
        notifyDataSetChanged()
    }
}


class BannerDiffCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}