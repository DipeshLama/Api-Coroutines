package com.example.apicoroutines.feature.resetPassword.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.model.request.ForgotPasswordRequest
import com.example.apicoroutines.feature.shared.repository.AuthRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {

    fun forgotPassword(request: ForgotPasswordRequest) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = repository.forgotPassword(request)))
            } catch (ex: Exception) {
                emit(Resource.error(data = null,
                    message = ex.message ?: "Something went wrong"))
            }
        }
}