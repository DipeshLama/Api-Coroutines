package com.example.apicoroutines.feature.shared.viewHolder

import com.example.apicoroutines.databinding.AdapterRoundProductsBinding
import com.example.apicoroutines.feature.shared.adapter.RoundProductAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.model.response.Tags

class RoundProductVH(val binding: AdapterRoundProductsBinding) : BaseViewHolder(binding.root) {
    fun bind(list : List<Tags>, title:String){
        binding.txvRoundTitle.text = title
        binding.ryvGifts.adapter = RoundProductAdapter(list)
    }
}