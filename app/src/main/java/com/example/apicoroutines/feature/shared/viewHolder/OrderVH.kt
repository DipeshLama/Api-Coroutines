package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutOrderHistoryBinding
import com.example.apicoroutines.feature.shared.listener.OrderTrackListener
import com.example.apicoroutines.feature.shared.model.response.Order

class OrderVH(val binding: LayoutOrderHistoryBinding, val listener: OrderTrackListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(order: Order) {
        binding.order = order
        binding.executePendingBindings()

        binding.btnTrackOrder.setOnClickListener {
            listener.onTrackOrder(adapterPosition)
        }
    }
}