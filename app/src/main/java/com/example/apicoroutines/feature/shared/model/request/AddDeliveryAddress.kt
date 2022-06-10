package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class AddDeliveryAddress(
    @SerializedName("title") var title: String? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("customer") var customer: String? = null,
    @SerializedName("contact_no") var contactNo: String? = null,
    @SerializedName("isDefault") var isDefault: Boolean? = null,
)