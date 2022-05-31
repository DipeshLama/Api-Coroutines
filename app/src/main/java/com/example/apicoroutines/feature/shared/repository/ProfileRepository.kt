package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class ProfileRepository @Inject constructor() : BaseRepository() {

    suspend fun profileShow(token : String) =
        apiService.profileShow(token)
}