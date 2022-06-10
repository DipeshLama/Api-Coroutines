package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutHomeCategoryBinding
import com.example.apicoroutines.feature.shared.model.response.Category

class HomeCategoryAdapter(val list: List<Category>) : RecyclerView.Adapter<HomeCategoryAdapter.CategoryVH>() {

    class CategoryVH (private val binding : LayoutHomeCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category : Category){
            binding.category = category
            Glide.with(itemView).load(category.backgroundImage)
                .into(binding.imvHomeCategoryIcon)
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(
            LayoutHomeCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.animation=
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.alpha)
    }

    override fun getItemCount() = list.size
}