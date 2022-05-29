package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("message")
    val message: String? = null
)