package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class PaymentRepository @Inject constructor() : BaseRepository() {

    suspend fun getPaymentMethod() =
        apiService.getPaymentMethod()
}