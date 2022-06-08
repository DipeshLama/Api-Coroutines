package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class OrderTracking(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("orderNumber") var orderNumber: String? = null,
    @SerializedName("orderDate") var orderDate: String? = null,
    @SerializedName("requestedDate") var requestedDate: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalTax") var totalTax: Int? = null,
    @SerializedName("serviceCharge") var serviceCharge: Int? = null,
    @SerializedName("nonTaxableAmount") var nonTaxableAmount: Int? = null,
    @SerializedName("serviceChargePercent") var serviceChargePercent: Int? = null,
    @SerializedName("discount") var discount: Int? = null,
    @SerializedName("deliveryCharge") var deliveryCharge: Int? = null,
    @SerializedName("couponDiscount") var couponDiscount: String? = null,
    @SerializedName("note") var note: String? = null,
    @SerializedName("scheme") var scheme: Int? = null,
    @SerializedName("orderAmount") var orderAmount: Int? = null,
    @SerializedName("taxableAmount") var taxableAmount: Int? = null,
    @SerializedName("subTotal") var subTotal: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("statusLog") var statusLog: ArrayList<StatusLog> = arrayListOf(),
    @SerializedName("coupon") var coupon: Coupon? = Coupon(),
    @SerializedName("deliveryAddress") var deliveryAddress: DeliveryAddress? = DeliveryAddress(),
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("paymentMethod") var paymentMethod: PaymentMethod? = PaymentMethod(),
    @SerializedName("paymentStatus") var paymentStatus: String? = null,
    @SerializedName("delivery") var delivery: Boolean? = null,
    @SerializedName("pickUp") var pickUp: PickUp? = PickUp(),
    @SerializedName("orderProducts") var orderProducts: ArrayList<OrderProducts> = arrayListOf(),
)