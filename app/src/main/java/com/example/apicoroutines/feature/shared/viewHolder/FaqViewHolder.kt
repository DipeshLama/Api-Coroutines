package com.example.apicoroutines.feature.shared.viewHolder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutFaqListBinding
import com.example.apicoroutines.feature.shared.listener.OnExpandListener
import com.example.apicoroutines.feature.shared.model.response.Faq

class FaqViewHolder(val binding: LayoutFaqListBinding, val listener: OnExpandListener) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener.onExpand(adapterPosition)
        }
    }

    fun bind(faq: Faq) {
        binding.faq = faq
        binding.executePendingBindings()

        when {
            faq.isExpanded -> checkIsExpanded(true)

            else -> checkIsExpanded(false)

        }
    }

    private fun checkIsExpanded(isExpanded: Boolean) {
        when {
            isExpanded -> {
                binding.expandableLayout.visibility = View.VISIBLE
                binding.rootFaqLayout.setBackgroundColor(Color.parseColor("#F0F0F0"))
            }

            else -> {
                binding.expandableLayout.visibility = View.GONE
                binding.rootFaqLayout.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}