package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartProducts {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("price")
    @Expose
    val price: Int? = null

    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null

    @SerializedName("selectedUnit")
    @Expose
    val selectedUnit: UnitPrice? = null

    @SerializedName("note")
    @Expose
    val note: String? = null

    @SerializedName("product")
    @Expose
    val product: Product? = null
}