package com.example.apicoroutines.feature.shared.listener

import com.example.apicoroutines.feature.shared.model.response.PaymentOptions

interface PassPaymentMethod {
    fun passPaymentMethod(paymentMethod : PaymentOptions)
}