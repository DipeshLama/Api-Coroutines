package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutAdsBannerBinding
import com.example.apicoroutines.feature.shared.model.response.Details

class AdsBannerAdapter(val list: List<Details>) :
    RecyclerView.Adapter<AdsBannerAdapter.AdsBanner>() {

    class AdsBanner(val binding: LayoutAdsBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(details: Details) {
            binding.detail = details
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsBanner {
        return AdsBanner(LayoutAdsBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: AdsBanner, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}