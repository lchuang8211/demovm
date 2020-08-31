package com.example.demovm.mainFunctionName.textBanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.databinding.TextBannerBinding
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel

class TextBannerViewHolder constructor(var binding: TextBannerBinding) : RecyclerView.ViewHolder(binding.root){
    companion object{
        fun from(parent: ViewGroup): TextBannerViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TextBannerBinding.inflate(layoutInflater,parent,false)
            return TextBannerViewHolder(binding)
        }

    }
    fun bind(item: String, viewModel: FunctionNameMainActivityViewModel){
        binding.viewModel = viewModel
        binding.tvBanner.text = item
    }
}