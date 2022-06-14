package com.example.apicoroutines.feature.shared.model.request

import com.example.apicoroutines.utils.constants.ApiConstants
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("client_id")
    var client_id: Int? = ApiConstants.client_id,

    @SerializedName("client_secret")
    var client_secret: String? = ApiConstants.client_secret,

    @SerializedName("grant_type")
    var grant_type: String? = ApiConstants.grant_type,

    @SerializedName("username")
    var username: String? = null,

    @SerializedName("password")
    var password: String? = null
)