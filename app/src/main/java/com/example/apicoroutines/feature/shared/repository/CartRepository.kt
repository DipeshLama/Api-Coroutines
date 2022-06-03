package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import com.example.apicoroutines.feature.shared.model.request.CartRequest
import com.example.apicoroutines.feature.shared.model.response.AddToCart
import javax.inject.Inject

class CartRepository @Inject constructor() : BaseRepository() {

    suspend fun addToCart (token : String, request : CartRequest)=
        apiService.addToCart(1, token , getGlobalUtils(request))

    suspend fun getUserCart (token : String) =
        apiService.getUserCart(1,token)
}