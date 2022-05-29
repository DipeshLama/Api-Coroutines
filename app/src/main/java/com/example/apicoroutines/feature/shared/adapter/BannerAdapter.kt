package com.example.apicoroutines.feature.shared.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.databinding.LayoutBannerBinding
import com.example.apicoroutines.feature.shared.model.response.Details

class BannerAdapter(val list: List<Details>) : RecyclerView.Adapter<BannerAdapter.BannerVH>() {

    class BannerVH(val binding: LayoutBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Details) {
            Glide.with(itemView).load(banner.images).into(binding.imvBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerVH {
        return BannerVH(LayoutBannerBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: BannerVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}