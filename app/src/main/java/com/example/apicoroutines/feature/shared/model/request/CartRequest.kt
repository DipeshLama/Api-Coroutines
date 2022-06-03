package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("productId")
    var productId: Int? = null,

    @SerializedName("priceId")
    var priceId: Int? = null,

    @SerializedName("quantity")
    var quantity: Int? = null,

    @SerializedName("note")
    var note: String? = null,
)