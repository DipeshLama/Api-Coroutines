package com.example.apicoroutines.feature.shared.viewHolder

import com.example.apicoroutines.databinding.AdapterHomeProductBinding
import com.example.apicoroutines.feature.shared.adapter.ProductAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Product

class ProductViewHolder(
    private val binding: AdapterHomeProductBinding,
    val listener: ProductClickListener,
) :
    BaseViewHolder(binding.root) {

    fun bind(list: List<Product>) {
        val productAdapter = ProductAdapter(list, listener)
        binding.ryvProducts.apply {
            adapter = productAdapter
        }
    }
}