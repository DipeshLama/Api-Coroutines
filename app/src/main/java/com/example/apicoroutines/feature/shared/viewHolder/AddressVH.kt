package com.example.apicoroutines.feature.shared.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutCheckoutAddressBinding
import com.example.apicoroutines.feature.shared.listener.OnAddressSelectedListener
import com.example.apicoroutines.feature.shared.listener.OnDeliveryAddressEdit
import com.example.apicoroutines.feature.shared.model.response.Address

class AddressVH(
    val binding: LayoutCheckoutAddressBinding,
    val listener: OnAddressSelectedListener,
    private val editListener : OnDeliveryAddressEdit

    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(address: Address) {
        binding.address = address
        binding.executePendingBindings()

        itemView.setOnClickListener {
            listener.onAddressSelected(adapterPosition)
        }
        binding.txvEditAddress.setOnClickListener{
            editListener.onDeliveryAddressEdit(address.id)
        }
        when (address.isDefault) {
            true -> checkIsSelected(true)
            else -> checkIsSelected(false)
        }
    }

    private fun checkIsSelected(isSelected: Boolean) {
        when {
            isSelected ->
                binding.imvSelectedAddress.visibility = View.VISIBLE

            else ->
                binding.imvSelectedAddress.visibility = View.GONE
        }
    }
}