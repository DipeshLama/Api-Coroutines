package com.example.apicoroutines.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.model.request.SignUpRequest
import com.example.apicoroutines.feature.shared.repository.AuthRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    fun signUp (request : SignUpRequest)  =  liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = repository.signup(request)))

        }catch (ex : Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
        }
    }
}