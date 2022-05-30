package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("first_name")
    val first_name: String? = null,

    @SerializedName("last_name")
    val last_name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("mobile_number")
    val mobile_number: String? = null,

    @SerializedName("password")
    val password: String? = null,
)