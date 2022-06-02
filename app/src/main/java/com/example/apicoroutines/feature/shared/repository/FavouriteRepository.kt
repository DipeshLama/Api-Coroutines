package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import com.example.apicoroutines.feature.shared.model.request.FavouriteRequest
import javax.inject.Inject

class FavouriteRepository @Inject constructor() : BaseRepository() {
    suspend fun addToFavourite (token : String, request : FavouriteRequest)=
        apiService.addToFavourite(1,token, getGlobalUtils(request))

    suspend fun getUserFavourite (token: String) =
        apiService.getUserFavourite(1,token)
}