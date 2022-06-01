package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("unitPrice")
    var unitPrice: List<UnitPrice>? = null,

    @SerializedName("images")
    var images: List<Images>? = null
)