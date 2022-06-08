package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName


data class OrderProducts(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("unit") var unit: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("productId") var productId: String? = null,
    @SerializedName("orderId") var orderId: Int? = null,
    @SerializedName("packageType") var packageType: String? = null,
    @SerializedName("productName") var productName: String? = null,
    @SerializedName("quantity") var quantity: Int? = null,
    @SerializedName("newQuantity") var newQuantity: Int? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("selectedunit") var selectedunit: String? = null,
    @SerializedName("size") var size: String? = null,
    @SerializedName("sku") var sku: String? = null,
    @SerializedName("barcode") var barcode: String? = null,
    @SerializedName("secondlevel_category_id") var secondLevelCategoryId: Int? = null,
    @SerializedName("secondlevel_category_title") var secondLevelCategoryTitle: String? = null
)