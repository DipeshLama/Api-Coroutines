package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutCheckoutAddressBinding
import com.example.apicoroutines.feature.shared.listener.OnAddressSelectedListener
import com.example.apicoroutines.feature.shared.listener.OnDeliveryAddressEdit
import com.example.apicoroutines.feature.shared.model.response.Address
import com.example.apicoroutines.feature.shared.viewHolder.AddressVH

class AddressAdapter(
    val list: ArrayList<Address>,
    val listener: OnAddressSelectedListener,
    private val editListener: OnDeliveryAddressEdit,
) :
    RecyclerView.Adapter<AddressVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVH {
        return AddressVH(LayoutCheckoutAddressBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), listener, editListener)
    }

    override fun onBindViewHolder(holder: AddressVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}