package com.example.apicoroutines.feature.shared.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutCartListBinding
import com.example.apicoroutines.feature.shared.listener.CartUpdateListener
import com.example.apicoroutines.feature.shared.model.response.Cart
import com.example.apicoroutines.feature.shared.model.response.CartProducts
import com.example.apicoroutines.utils.helper.DecimalHelper

class CartViewHolder(val binding: LayoutCartListBinding, val listener: CartUpdateListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cart: CartProducts) {
        binding.cartProducts = cart
        binding.executePendingBindings()

        if (cart.quantity!! > 1) {
            binding.imvCartDecrease.visibility = View.VISIBLE
            binding.imvCartDelete.visibility = View.GONE
        } else {
            binding.imvCartDecrease.visibility = View.GONE
            binding.imvCartDelete.visibility = View.VISIBLE
        }

        binding.imvCartIncrease.setOnClickListener {
            listener.onCartIncrease(adapterPosition, cart.id)
        }

        binding.imvCartDecrease.setOnClickListener {
            listener.onCartDecrease(adapterPosition, cart.id)
        }

        binding.imvCartDelete.setOnClickListener {
            listener.onDelete(adapterPosition, cart.id)
        }
    }
}