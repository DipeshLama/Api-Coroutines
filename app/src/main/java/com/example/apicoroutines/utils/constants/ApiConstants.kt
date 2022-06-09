package com.example.apicoroutines.utils.constants

object ApiConstants {
    const val baseUrl = "https://uat.ordering-iamthegardener-v4.ekbana.net/"
    const val apiKey = "c0e054eac239d5b973d754909d5b676551bbbe4f44285f20524b23a1908afb15"
    const val apiKeyString = "Api-key"
    const val wareHouseIdString = "Warehouse-Id"
    const val client_id = 2
    const val client_secret = "IY1z0KiePtEYkZgcBoyZsOOoVEzyd5Jn6PGqYO1u"
    const val grant_type = "password"
    const val cartProductId = "cartProductId"
    const val authorization = "Authorization"
    const val orderId = "orderId"

    //End points
    private const val noAuth = "api/v4"
    private const val  auth = "api/v4/auth"
    const val login_url = "$auth/login"
    const val signUp_url = "$auth/signup"
    const val forgotPassword = "$auth/forgot-password"
    const val changePassword = "$noAuth/profile/change-password"

    // HomeScreen
    const val newHomePage = "$noAuth/newhome"

    const val category = "$noAuth/category"

    const val profileShow  = "$noAuth/profile/show"

    const val getCart = "$noAuth/cart"
    const val productDetail = "$noAuth/product/{productId}"
    const val productId = "productId"
    const val favourite = "$noAuth/favourite"
    const val addToCart = "$noAuth/cart-product"
    const val updateCart = "$noAuth/cart-product/{cartProductId}"
    const val order = "$noAuth/order"
    const val orderById = "$order/{orderId}"
    const val paymentMethod = "$noAuth/payment-method"
}