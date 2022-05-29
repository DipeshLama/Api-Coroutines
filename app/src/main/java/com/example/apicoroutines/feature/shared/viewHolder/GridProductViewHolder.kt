package com.example.apicoroutines.feature.shared.viewHolder

import com.example.apicoroutines.databinding.AdapterGridProductBinding
import com.example.apicoroutines.feature.shared.adapter.GridProductAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.model.response.Product

class GridProductViewHolder(val binding : AdapterGridProductBinding) : BaseViewHolder(binding.root) {

    fun bind (list : ArrayList<Product>){
        binding
        binding.ryvGridProduct.adapter = GridProductAdapter(list)
    }
}