package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutGridProductBinding
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.helper.DecimalHelper

class GridProductAdapter(val list: List<Product>, val listener: ProductClickListener) :
    RecyclerView.Adapter<GridProductAdapter.GridVH>() {

    class GridVH(
        val binding: LayoutGridProductBinding,
        val listener: ProductClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridVH {
        return GridVH(
            LayoutGridProductBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false), listener)
    }

    override fun onBindViewHolder(holder: GridVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.alpha)
    }

    override fun getItemCount() = list.size
}