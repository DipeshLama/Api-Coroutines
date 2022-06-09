package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutPaymentOptionsBinding
import com.example.apicoroutines.feature.shared.model.response.PaymentOptions
import com.example.apicoroutines.feature.shared.viewHolder.PaymentVH

class PaymentAdapter(val list: ArrayList<PaymentOptions>) :
    RecyclerView.Adapter<PaymentVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentVH {
        return PaymentVH(LayoutPaymentOptionsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: PaymentVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}