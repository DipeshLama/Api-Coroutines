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
        binding.txvCartTitle.text = cart.product?.title

        binding.txvCartSinglePrice.text =
            String.format(binding.root.context.getString(R.string.nrs_),
                DecimalHelper.getRoundedOffPriceRs(cart.product?.unitPrice?.get(0)?.markedPrice
                    ?: 0))

        binding.txvCartTotalPrice.text =
            String.format(binding.root.context.getString(R.string.nrs_),
                DecimalHelper.getRoundedOffPriceRs(cart.price ?: 0))

        binding.txvCartQuantity.text = cart.quantity.toString()

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