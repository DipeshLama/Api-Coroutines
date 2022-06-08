package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutOrderHistoryBinding
import com.example.apicoroutines.feature.shared.listener.OrderTrackListener
import com.example.apicoroutines.feature.shared.model.response.Order
import com.example.apicoroutines.feature.shared.viewHolder.OrderVH

class OrderAdapter(val list: ArrayList<Order>, val listener : OrderTrackListener) : RecyclerView.Adapter<OrderVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVH {
        return OrderVH(LayoutOrderHistoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), listener)
    }

    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}