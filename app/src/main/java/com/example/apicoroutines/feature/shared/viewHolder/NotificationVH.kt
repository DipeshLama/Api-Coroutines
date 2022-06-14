package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutNotificationListBinding
import com.example.apicoroutines.feature.shared.model.response.PushNotification

class NotificationVH(val binding: LayoutNotificationListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(notification: PushNotification) {
        binding.notification = notification
        binding.executePendingBindings()
    }
}