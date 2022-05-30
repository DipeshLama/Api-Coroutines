package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import com.example.apicoroutines.feature.shared.model.request.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor() : BaseRepository() {

    suspend fun login (request : LoginRequest) = apiService.login(getGlobalUtils(request))
}