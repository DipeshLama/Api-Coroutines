package com.example.apicoroutines.feature.paymentOptions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.repository.PaymentRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PaymentOptionsViewModel @Inject constructor(val repository: PaymentRepository) : ViewModel() {
    fun getPaymentMethod () = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = repository.getPaymentMethod() ))
        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong!"))
        }
    }
}