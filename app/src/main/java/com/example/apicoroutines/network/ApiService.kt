package com.example.apicoroutines.network

import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.feature.shared.model.response.Login
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.constants.ApiConstants
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(ApiConstants.login_url)
    suspend fun login(
        @Body request : RequestBody
    ) : Response<Login>

    @GET(ApiConstants.newHomePage)
    suspend fun getHomeScreenData(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
    ): Response<BaseArrayResponse<Home>>
}