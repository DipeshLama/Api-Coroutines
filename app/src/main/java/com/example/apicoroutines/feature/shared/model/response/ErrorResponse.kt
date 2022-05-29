package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    var errors: List<Error?>? = null
)