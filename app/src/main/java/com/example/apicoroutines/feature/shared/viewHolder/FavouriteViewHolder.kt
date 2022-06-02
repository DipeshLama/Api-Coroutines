package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.databinding.LayoutFavouriteBinding
import com.example.apicoroutines.feature.shared.model.response.Favourite

class FavouriteViewHolder(val binding: LayoutFavouriteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(favourite: Favourite) {
        binding.txvFavTitle.text = favourite.product?.title
        binding.txvFavPrice.text = favourite.product?.unitPrice?.get(0)?.markedPrice.toString()
        Glide.with(itemView).load(favourite.product?.images?.get(0)?.imageName)
            .into(binding.imvFavImage)
    }
}