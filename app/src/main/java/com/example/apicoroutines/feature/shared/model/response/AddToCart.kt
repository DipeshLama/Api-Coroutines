package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddToCart {

    @SerializedName("id")
    private val id: Int? = null

    @SerializedName("price")
    private val price: Int? = null

    @SerializedName("quantity")
    private val quantity: Int? = null

    @SerializedName("selectedUnit")
    private val selectedUnit: UnitPrice? = null

    @SerializedName("note")
    private val note: String? = null

    @SerializedName("product")
    private val product: Product? = null
}