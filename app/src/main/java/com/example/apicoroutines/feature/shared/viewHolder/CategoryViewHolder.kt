package com.example.apicoroutines.feature.shared.viewHolder

import com.example.apicoroutines.databinding.AdapterHomeCategoryBinding
import com.example.apicoroutines.feature.shared.adapter.CategoryAdapter
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.model.response.Category

class CategoryViewHolder(private val binding: AdapterHomeCategoryBinding) : BaseViewHolder(binding.root){

    fun bind(list: List<Category>) {
        val categoryAdapter = CategoryAdapter(list)
        binding.ryvCategory.apply {
            adapter = categoryAdapter
        }
    }
}