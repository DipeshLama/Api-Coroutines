package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class OrderRepository @Inject constructor() : BaseRepository() {
    suspend fun getUserOrder(token: String) =
        apiService.getUserOrder(token)

    suspend fun getUserOrderById (token: String, orderId : Int) =
        apiService.getOrderById(token, orderId)
}