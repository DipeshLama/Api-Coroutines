package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class ProductDetailRepository @Inject constructor()  : BaseRepository() {
    suspend fun getProductDetail(productId : Int) =
        apiService.getProductDetail(1,productId)
}