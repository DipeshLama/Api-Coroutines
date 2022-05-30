package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("client_id")
    var client_id: Int? = null,

    @SerializedName("client_secret")
    var client_secret: String? = null,

    @SerializedName("grant_type")
    var grant_type: String? = null,

    @SerializedName("username")
    var username: String? = null,

    @SerializedName("password")
    var password: String? = null
)