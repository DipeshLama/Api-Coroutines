package com.example.apicoroutines.feature.shared.viewHolder

import com.example.apicoroutines.databinding.AdapterHomeCategoryBinding
import com.example.apicoroutines.feature.shared.adapter.HomeCategoryAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.model.response.Category

class HomeCategoryViewHolder(private val binding: AdapterHomeCategoryBinding) : BaseViewHolder(binding.root){

    fun bind(list: List<Category>) {
        val categoryAdapter = HomeCategoryAdapter(list)
        binding.ryvCategory.apply {
            adapter = categoryAdapter
        }
    }
}