package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutFavouriteBinding
import com.example.apicoroutines.feature.shared.model.response.Favourite
import com.example.apicoroutines.feature.shared.viewHolder.FavouriteViewHolder

class FavouriteAdapter(val list: ArrayList<Favourite>) :
    RecyclerView.Adapter<FavouriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(LayoutFavouriteBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}