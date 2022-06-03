package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class UpdateCart(
    @SerializedName("quantity")
    var quantity: Int? = null,
    var note : String? = null
)