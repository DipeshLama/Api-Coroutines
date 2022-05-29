package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class UnitPrice(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("sellingPrice")
    var sellingPrice: Int? = null,

    @SerializedName("markedPrice")
    var markedPrice: Int? = null,

    @SerializedName("newPrice")
    var newPrice: Int? = null,

    @SerializedName("oldPrice")
    var oldPrice: Int? = null,

    @SerializedName("size")
    var size: String? = null,

    @SerializedName("sku")
    var sku: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("barcode")
    var barcode: String? = null,

    @SerializedName("stock")
    var stock: Int? = null,

    @SerializedName("hasOffer")
    var hasOffer: Boolean? = null,

    @SerializedName("alwaysAvailable")
    var alwaysAvailable: Boolean? = null,
)