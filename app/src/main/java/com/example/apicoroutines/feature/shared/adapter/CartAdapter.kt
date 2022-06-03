package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutCartListBinding
import com.example.apicoroutines.feature.shared.listener.CartUpdateListener
import com.example.apicoroutines.feature.shared.model.response.CartProducts
import com.example.apicoroutines.feature.shared.viewHolder.CartViewHolder

class CartAdapter(val list: ArrayList<CartProducts>, val listener : CartUpdateListener) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutCartListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), listener)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}