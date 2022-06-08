package com.example.apicoroutines.feature.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.repository.OrderRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(val repository: OrderRepository) : ViewModel() {
    fun getUserOrder (token : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = repository.getUserOrder(token) ))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong!"))
        }
    }

    fun getUserOrderById (token : String, orderId : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = repository.getUserOrderById(token,orderId) ))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong!"))
        }
    }
}