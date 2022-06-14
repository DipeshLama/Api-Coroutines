package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutNotificationListBinding
import com.example.apicoroutines.feature.shared.model.response.PushNotification
import com.example.apicoroutines.feature.shared.viewHolder.NotificationVH

class NotificationListAdapter(val list: ArrayList<PushNotification>) :
    RecyclerView.Adapter<NotificationVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationVH {
        return NotificationVH(LayoutNotificationListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NotificationVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}