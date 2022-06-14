package com.example.apicoroutines.feature.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.repository.NotificationRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: NotificationRepository) :
    ViewModel() {

    fun getPushNotification(token: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPushNotification(token)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong!"))
        }
    }
}