package com.example.apicoroutines.network

import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.constants.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstants.newHomePage)
    suspend fun getHomeScreenData(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
    ): Response<BaseArrayResponse<Home>>
}