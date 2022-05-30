package com.example.apicoroutines.network

import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.response.*
import com.example.apicoroutines.utils.constants.ApiConstants
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(ApiConstants.login_url)
    suspend fun login(
        @Body request: RequestBody,
    ): Response<Login>

    @POST(ApiConstants.signUp_url)
    suspend fun signup(
        @Body request: RequestBody,
    ): Response<BaseResponse<SignUp>>

    @GET(ApiConstants.newHomePage)
    suspend fun getHomeScreenData(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
    ): Response<BaseArrayResponse<Home>>
}