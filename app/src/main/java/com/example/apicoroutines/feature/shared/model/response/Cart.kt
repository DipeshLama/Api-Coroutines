package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cart {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("cartNumber")
    val cartNumber: String? = null

    @SerializedName("categoryId")
    val categoryId: Any? = null

    @SerializedName("warehouseId")
    val warehouseId: Int? = null

    @SerializedName("orderAmount")
    val orderAmount: Int? = null

    @SerializedName("discount")
    val discount: Float? = null

    @SerializedName("scheme")
    val scheme: Int? = null

    @SerializedName("subTotal")
    val subTotal: Float? = null

    @SerializedName("deliveryCharge")
    val deliveryCharge: Int? = null

    @SerializedName("extra")
    val extra: List<Extra>? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("campaign_message")
    val campaignMessage: String? = null

    @SerializedName("total")
    val total: Int? = null

    @SerializedName("pickupTotal")
    val pickupTotal: Int? = null

    @SerializedName("cartProducts")
    val cartProducts: List<CartProducts>? = null
}