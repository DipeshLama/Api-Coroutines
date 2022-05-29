package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.AdapterBannerBinding
import com.example.apicoroutines.feature.shared.adapter.BannerAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.model.response.Details

class BannerViewHolder(val binding : AdapterBannerBinding) : BaseViewHolder(binding.root) {
    fun bind (details : List<Details>){
        binding.vpBanner.adapter = BannerAdapter(details)
    }
}