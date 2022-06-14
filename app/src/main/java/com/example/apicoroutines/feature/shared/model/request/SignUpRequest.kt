package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("first_name")
    var firstName: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("mobile_number")
    var mobileNumber: String? = null,

    @SerializedName("password")
    var password: String? = null
)