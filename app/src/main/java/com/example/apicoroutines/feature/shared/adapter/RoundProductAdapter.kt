package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutGiftsBinding
import com.example.apicoroutines.feature.shared.model.response.Tags

class RoundProductAdapter(val list: List<Tags>) :
    RecyclerView.Adapter<RoundProductAdapter.RoundVH>() {

    class RoundVH(val binding: LayoutGiftsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: Tags) {
            binding.tag = tag
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundVH {
        return RoundVH(LayoutGiftsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RoundVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}