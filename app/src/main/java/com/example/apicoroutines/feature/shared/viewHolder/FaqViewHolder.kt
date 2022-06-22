package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutFaqListBinding
import com.example.apicoroutines.feature.shared.listener.OnExpandListener
import com.example.apicoroutines.feature.shared.model.response.Faq

class FaqViewHolder(
    val binding: LayoutFaqListBinding,
    val listener: OnExpandListener,
    val size: Int,
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener.onExpand(adapterPosition)
        }
    }

    fun bind(faq: Faq) {
        binding.faq = faq
        binding.executePendingBindings()

        if (faq.isExpanded) {
            binding.txvFaqAns.animate().setDuration(500).alpha(1.0f)
            binding.imvExpand.animate().rotation(180f)
        } else {
            binding.txvFaqAns.animate().setDuration(500).alpha(0.0f)
            binding.imvExpand.animate().rotation(0f)
        }
    }
}