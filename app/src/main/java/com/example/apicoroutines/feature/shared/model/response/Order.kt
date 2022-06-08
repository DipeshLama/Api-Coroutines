package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("orderNumber") var orderNumber: String? = null,
    @SerializedName("orderDate") var orderDate: String? = null,
    @SerializedName("requestedDate") var requestedDate: String? = null,
    @SerializedName("orderProductsCount") var orderProductsCount: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalTax") var totalTax: Int? = null,
    @SerializedName("pickUp") var pickUp: Boolean? = null,
    @SerializedName("serviceCharge") var serviceCharge: Int? = null,
    @SerializedName("discount") var discount: Int? = null,
    @SerializedName("deliveryCharge") var deliveryCharge: Int? = null,
    @SerializedName("couponDiscount") var couponDiscount: String? = null,
    @SerializedName("orderAmount") var orderAmount: Int? = null,
    @SerializedName("taxableAmount") var taxableAmount: Int? = null,
    @SerializedName("scheme") var scheme: Int? = null,
    @SerializedName("subTotal") var subTotal: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("paymentStatus") var paymentStatus: String? = null,
    @SerializedName("coupon") var coupon: Coupon? = Coupon(),
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,

    )