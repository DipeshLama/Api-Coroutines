package com.example.apicoroutines.utils.constants

object ApiConstants {
    const val baseUrl = "https://uat.ordering-iamthegardener-v4.ekbana.net/"
    const val apiKey = "c0e054eac239d5b973d754909d5b676551bbbe4f44285f20524b23a1908afb15"
    const val apiKeyString = "Api-key"
    const val wareHouseIdString = "Warehouse-Id"
    const val client_id = 2
    const val client_secret = "IY1z0KiePtEYkZgcBoyZsOOoVEzyd5Jn6PGqYO1u"
    const val grant_type = "password"

    const val authorization = "Authorization"

    private const val noAuth = "api/v4"

    const val login_url = "api/v4/auth/login"
    const val signUp_url = "api/v4/auth/signup"
    const val forgotPassword = "api/v4/auth/forgot-password"

    // HomeScreen
    const val newHomePage = "api/v4/newhome"

    const val category = "api/v4/category"

    const val profileShow  = "$noAuth/profile/show"

    const val getCart = "$noAuth/cart"

}