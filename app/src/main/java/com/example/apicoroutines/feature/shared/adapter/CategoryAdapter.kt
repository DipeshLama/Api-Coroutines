package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutCategoriesBinding
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.viewHolder.CategoryVH

class CategoryAdapter(val list: ArrayList<Category>) : RecyclerView.Adapter<CategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(LayoutCategoriesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.alpha)
    }

    override fun getItemCount() = list.size
}