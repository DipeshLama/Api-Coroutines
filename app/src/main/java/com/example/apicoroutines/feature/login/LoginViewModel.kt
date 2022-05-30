package com.example.apicoroutines.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.base.BaseRepository
import com.example.apicoroutines.feature.shared.model.request.LoginRequest
import com.example.apicoroutines.feature.shared.repository.AuthRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    fun login(request: LoginRequest) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = repository.login(request)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message ?: " Something went wrong"))
        }
    }
}