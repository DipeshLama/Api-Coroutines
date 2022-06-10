package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("customer") var customer: String? = null,
    @SerializedName("contactNo") var contactNo: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("detail") var detail: AddressDetail? = AddressDetail(),
    @SerializedName("isDefault") var isDefault: Boolean? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
)