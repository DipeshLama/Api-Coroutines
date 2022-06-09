package com.example.apicoroutines.network

import androidx.lifecycle.LiveData
import androidx.room.Delete
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

    @POST(ApiConstants.changePassword)
    suspend fun changePassword(
        @Body request: RequestBody,
        @Header(ApiConstants.authorization) token: String,
    ): Response<BaseResponse<ProfileShow>>

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

    @POST(ApiConstants.addToCart)
    suspend fun addToCart(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Header(ApiConstants.authorization) token: String,
        @Body request: RequestBody,
    ): Response<BaseResponse<AddToCart>>

    @GET(ApiConstants.getCart)
    suspend fun getUserCart(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Header(ApiConstants.authorization) token: String,
    ): Response<BaseResponse<Cart>>

    @PATCH(ApiConstants.updateCart)
    suspend fun updateUserCart(
        @Header(ApiConstants.authorization) token: String,
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Path(ApiConstants.cartProductId) cartProductId: Int,
        @Body request: RequestBody,
    ): Response<BaseResponse<CartProducts>>

    @DELETE(ApiConstants.updateCart)
    suspend fun deleteCartProduct(
        @Header(ApiConstants.authorization) token: String,
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Path(ApiConstants.cartProductId) cartProductId: Int,
    ): Response<String>

    @GET(ApiConstants.productDetail)
    suspend fun getProductDetail(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Path(ApiConstants.productId) id: Int,
    ): Response<BaseResponse<Product>>

    @POST(ApiConstants.favourite)
    suspend fun addToFavourite(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Header(ApiConstants.authorization) token: String,
        @Body request: RequestBody,
    ): Response<BaseResponse<Favourite>>

    @GET(ApiConstants.favourite)
    suspend fun getUserFavourite(
        @Header(ApiConstants.wareHouseIdString) wareHouseId: Int,
        @Header(ApiConstants.authorization) token: String,
    ): Response<BaseArrayResponse<Favourite>>


    @GET(ApiConstants.order)
    suspend fun getUserOrder(
        @Header(ApiConstants.authorization) token: String,
    ): Response<BaseArrayResponse<Order>>


    @GET(ApiConstants.orderById)
    suspend fun getOrderById(
        @Header(ApiConstants.authorization) token: String,
        @Path(ApiConstants.orderId) orderId: Int,
    ): Response<BaseResponse<OrderTracking>>

    @GET(ApiConstants.paymentMethod)
    suspend fun getPaymentMethod(): Response<BaseArrayResponse<PaymentOptions>>
}