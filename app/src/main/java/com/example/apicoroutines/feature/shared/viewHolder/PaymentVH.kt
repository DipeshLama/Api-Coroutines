package com.example.apicoroutines.feature.shared.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutPaymentOptionsBinding
import com.example.apicoroutines.feature.shared.model.response.PaymentOptions

class PaymentVH(val binding: LayoutPaymentOptionsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(paymentOptions: PaymentOptions) {
        binding.txvPaymentTitle.text= paymentOptions.title
    }
}