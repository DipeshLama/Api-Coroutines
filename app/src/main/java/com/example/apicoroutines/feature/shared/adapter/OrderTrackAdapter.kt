package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutOrderTrackingBinding
import com.example.apicoroutines.feature.shared.model.response.OrderTracking
import com.example.apicoroutines.feature.shared.viewHolder.OrderTrackVH

class OrderTrackAdapter(private val orderTrack: OrderTracking) :
    RecyclerView.Adapter<OrderTrackVH>() {

    var position: Int? = -1

    init {
        orderTrack.status = "Delivering"
        position = orderTrack.statusLog.filter {
            it.status == orderTrack.status
        }[0].position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderTrackVH {
        return OrderTrackVH(LayoutOrderTrackingBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),
            orderTrack.status.toString(),
            orderTrack.statusLog.size,
            position ?: 0
        )
    }

    override fun onBindViewHolder(holder: OrderTrackVH, position: Int) {
        holder.bind(orderTrack.statusLog[position])
    }

    override fun getItemCount() = orderTrack.statusLog.size
}