package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.AdaptorTitleBinding
import com.example.apicoroutines.feature.shared.base.BaseViewHolder

class TitleVH(val binding: AdaptorTitleBinding) : BaseViewHolder(binding.root) {
    fun bind(title: String) {
        binding.txtTitle.text = title
    }
}