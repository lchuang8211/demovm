package com.example.demovm.mainFunctionName.subFunctionNameOne.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demovm.AppUtils
import com.example.demovm.data.source.local.imagebanner.ImageBanner
import com.example.demovm.databinding.ImageViewBannerBinding
import com.example.demovm.mainFunctionName.subFunctionNameOne.MainFragmentViewModel

class ImageBannerViewHolder(var binding: ImageViewBannerBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup) : ImageBannerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ImageViewBannerBinding.inflate(layoutInflater, parent,false)
            binding.ivBannerItem.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            binding.ivBannerItem.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            return ImageBannerViewHolder(binding)
        }
    }

    fun bind(imageBanner: ImageBanner, viewModel: MainFragmentViewModel){
        binding.imagebanner = imageBanner
        binding.ivBannerItem.setBackgroundResource(AppUtils.getResources().getIdentifier(imageBanner.imgURL, "drawable", "com.example.demovm"))
        binding.ivBannerItem.setOnClickListener(View.OnClickListener {
            Toast.makeText(AppUtils.getContext(),imageBanner.imgName,Toast.LENGTH_SHORT).show()
        })
    }
}