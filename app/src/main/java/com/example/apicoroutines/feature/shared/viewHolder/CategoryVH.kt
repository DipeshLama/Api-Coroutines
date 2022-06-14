package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.databinding.LayoutCategoriesBinding
import com.example.apicoroutines.feature.shared.model.response.Category

class CategoryVH(val binding: LayoutCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.category = category
        binding.executePendingBindings()
    }
}