package com.example.apicoroutines.feature.shared.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutPaymentOptionsBinding
import com.example.apicoroutines.feature.shared.listener.OnPaymentMethodSelectListener
import com.example.apicoroutines.feature.shared.listener.PassPaymentMethod
import com.example.apicoroutines.feature.shared.model.response.PaymentOptions

class PaymentVH(
    val binding: LayoutPaymentOptionsBinding,
    val listener: OnPaymentMethodSelectListener) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener.onPaymentSelect(adapterPosition)
        }
    }

    fun bind(paymentOptions: PaymentOptions) {
        binding.txvPaymentTitle.text = paymentOptions.title

        when {
            paymentOptions.isSelected -> {
                checkSelected(true)
            }
            else -> checkSelected(false)
        }
    }

    private fun checkSelected(isSelected: Boolean) {
        if (isSelected) {
            binding.cvPaymentSelected.visibility = View.VISIBLE
        } else {
            binding.cvPaymentSelected.visibility = View.GONE
        }
    }
}