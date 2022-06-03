package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutCartListBinding
import com.example.apicoroutines.feature.shared.model.response.Cart
import com.example.apicoroutines.feature.shared.model.response.CartProducts

class CartViewHolder (val binding : LayoutCartListBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind (cart: CartProducts){
        binding.txvCartTitle.text = cart.product?.title
        binding.txvCartSinglePrice.text = cart.product?.unitPrice?.get(0)?.markedPrice.toString()
        binding.txvCartTotalPrice.text = cart.price.toString()
    }
}