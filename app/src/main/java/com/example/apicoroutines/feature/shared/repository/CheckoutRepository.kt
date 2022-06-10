package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import com.example.apicoroutines.feature.shared.model.request.AddDeliveryAddress
import javax.inject.Inject

class CheckoutRepository @Inject constructor() : BaseRepository() {

    suspend fun addDeliveryAddress(token: String, request: AddDeliveryAddress) =
        apiService.addDeliveryAddress(token, getGlobalUtils(request))

    suspend fun getDeliveryAddress(token: String) =
        apiService.getDeliveryAddress(token)

    suspend fun getDeliveryAddressById(token : String, addressId: Int)=
        apiService.getDeliveryAddressById(token, addressId)

    suspend fun updateDeliveryAddress(token: String, addressId : Int,request: AddDeliveryAddress ) =
        apiService.updateDeliveryAddress(token, addressId, getGlobalUtils(request))
}