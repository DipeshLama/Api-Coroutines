package com.example.apicoroutines.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.model.request.CartRequest
import com.example.apicoroutines.feature.shared.model.request.UpdateCart
import com.example.apicoroutines.feature.shared.repository.CartRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository :  CartRepository) : ViewModel() {

    fun addToCart (token : String, request : CartRequest) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = repository.addToCart(token,request)))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?:"Something went wrong"))
        }
    }

    fun getUserCart(token : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try{
            emit(Resource.success(data = repository.getUserCart(token)))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
        }
    }

    fun updateCart(token : String, cartProductId : Int, request : UpdateCart) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try{
            emit(Resource.success(data = repository.updateCart(token,cartProductId,request)))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
        }
    }

    fun deleteCart(token : String, cartProductId : Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try{
            emit(Resource.success(data = repository.deleteCart(token,cartProductId)))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
        }
    }
}