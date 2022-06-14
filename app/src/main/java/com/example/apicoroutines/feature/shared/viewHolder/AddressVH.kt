package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutCheckoutAddressBinding
import com.example.apicoroutines.feature.shared.listener.OnAddressSelectedListener
import com.example.apicoroutines.feature.shared.listener.OnDeliveryAddressEdit
import com.example.apicoroutines.feature.shared.model.response.Address

class AddressVH(
    val binding: LayoutCheckoutAddressBinding,
    val listener: OnAddressSelectedListener,
    private val editListener: OnDeliveryAddressEdit,

    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(address: Address) {
        binding.address = address
        binding.listener = editListener
        binding.executePendingBindings()

        itemView.setOnClickListener {
            listener.onAddressSelected(adapterPosition)
        }
    }
}