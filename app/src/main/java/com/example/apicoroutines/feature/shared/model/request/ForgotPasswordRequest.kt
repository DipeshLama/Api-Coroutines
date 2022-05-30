package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("email")
    var email: String? = null,
)