package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.databinding.LayoutGridProductBinding
import com.example.apicoroutines.feature.shared.model.response.Product

class GridProductAdapter(val list: List<Product>) :
    RecyclerView.Adapter<GridProductAdapter.GridVH>() {

    class GridVH(val binding: LayoutGridProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
            Glide.with(itemView).load(product.images?.get(0)?.imageName)
                .into(binding.imvGridProductImage)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridVH {
        return GridVH(
            LayoutGridProductBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun onBindViewHolder(holder: GridVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}