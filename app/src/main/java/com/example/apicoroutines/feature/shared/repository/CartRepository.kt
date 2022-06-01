package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class CartRepository @Inject constructor() : BaseRepository() {
    suspend fun getUserCart (token : String) =
        apiService.getUserCart(1,token)
}