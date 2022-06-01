package com.example.apicoroutines.network

import androidx.lifecycle.LiveData
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

    @POST(ApiConstants.forgotPassword)
    suspend fun forgotPassword(
        @Body request: RequestBody,
    ): Response<BaseResponse<SignUp>>

    @GET(ApiConstants.newHomePage)
    suspend fun getHomeScreenData(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
    ): Response<BaseArrayResponse<Home>>

    @GET(ApiConstants.category)
    suspend fun getCategories(): Response<BaseArrayResponse<Category>>

    @GET(ApiConstants.profileShow)
    suspend fun profileShow(
        @Header(ApiConstants.authorization) token: String,
    ): Response<BaseResponse<ProfileShow>>

    @GET(ApiConstants.getCart)
    suspend fun getUserCart(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Header(ApiConstants.authorization) token : String
    ) : Response<BaseResponse<Cart>>

    @GET(ApiConstants.productDetail)
    suspend fun getProductDetail(
        @Header(ApiConstants.wareHouseIdString) wareHouseId : Int,
        @Path(ApiConstants.productId) id : Int
    ) : Response<BaseResponse<Product>>
}