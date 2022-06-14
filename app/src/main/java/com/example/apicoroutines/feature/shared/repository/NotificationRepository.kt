package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class NotificationRepository @Inject constructor() : BaseRepository() {

    suspend fun getPushNotification(token: String) =
        apiService.getPushNotification(token)
}