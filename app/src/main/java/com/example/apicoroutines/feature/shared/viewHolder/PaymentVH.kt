package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutPaymentOptionsBinding
import com.example.apicoroutines.feature.shared.listener.OnPaymentMethodSelectListener
import com.example.apicoroutines.feature.shared.model.response.PaymentOptions

class PaymentVH(
    val binding: LayoutPaymentOptionsBinding,
    val listener: OnPaymentMethodSelectListener,
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener.onPaymentSelect(adapterPosition)
        }
    }

    fun bind(paymentOptions: PaymentOptions) {
        binding.paymentOptions = paymentOptions
        binding.executePendingBindings()
    }
}