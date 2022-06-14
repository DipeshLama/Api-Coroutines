package com.example.apicoroutines.feature.shared.viewHolder

import com.example.apicoroutines.databinding.AdapterAdsBannerBinding
import com.example.apicoroutines.feature.shared.adapter.AdsBannerAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.model.response.Details

class AdsBannerVH(val binding : AdapterAdsBannerBinding) : BaseViewHolder(binding.root) {
    fun bind(list : List<Details>){
        binding.ryvAdsBanner.adapter = AdsBannerAdapter(list)
    }
}