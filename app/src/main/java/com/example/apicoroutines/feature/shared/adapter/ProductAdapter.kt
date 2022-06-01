package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.databinding.LayoutProductHorizontalBinding
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Product

class ProductAdapter(private val list: List<Product>, val listener: ProductClickListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: LayoutProductHorizontalBinding,
        val listener: ProductClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            Glide.with(itemView).load(product.images?.get(0)?.imageName)
                .into(binding.imvHomeHorizontalProductImage)
            binding.executePendingBindings()
            itemView.setOnClickListener {
                listener.onHomeProductClick(product.id ?: 0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutProductHorizontalBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}