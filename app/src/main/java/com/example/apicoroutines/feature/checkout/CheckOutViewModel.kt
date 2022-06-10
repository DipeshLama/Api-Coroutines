package com.example.apicoroutines.feature.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.model.request.AddDeliveryAddress
import com.example.apicoroutines.feature.shared.repository.CartRepository
import com.example.apicoroutines.feature.shared.repository.CheckoutRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(val repository: CheckoutRepository) : ViewModel() {
    fun addDeliveryAddress(token: String, request: AddDeliveryAddress) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = repository.addDeliveryAddress(token, request)))

        } catch (ex: Exception) {
            emit(Resource.error(message = ex.message ?: "Something went wrong", data = null))
        }
    }

    fun getDeliveryAddress(token: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = repository.getDeliveryAddress(token)))

        } catch (ex: Exception) {
            emit(Resource.error(message = ex.message ?: "Something went wrong", data = null))
        }
    }

    fun getDeliveryAddressById(token: String, addressId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = repository.getDeliveryAddressById(token, addressId)))

        } catch (ex: Exception) {
            emit(Resource.error(message = ex.message ?: "Something went wrong", data = null))
        }
    }

    fun updateDeliveryAddress(token: String, addressId: Int, request: AddDeliveryAddress) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))

            try {
                emit(Resource.success(data = repository.updateDeliveryAddress(
                    token,
                    addressId,
                    request)))

            } catch (ex: Exception) {
                emit(Resource.error(message = ex.message ?: "Something went wrong", data = null))
            }
        }
}