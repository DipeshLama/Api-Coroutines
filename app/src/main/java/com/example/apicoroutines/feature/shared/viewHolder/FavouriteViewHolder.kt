package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.databinding.LayoutFavouriteBinding
import com.example.apicoroutines.feature.shared.model.response.Favourite

class FavouriteViewHolder(val binding: LayoutFavouriteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(favourite: Favourite) {
        binding.favourite = favourite
        binding.executePendingBindings()
    }
}