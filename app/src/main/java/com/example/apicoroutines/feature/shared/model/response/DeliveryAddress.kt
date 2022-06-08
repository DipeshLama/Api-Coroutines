package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName


data class DeliveryAddress(

    @SerializedName("customerName") var customerName: String? = null,
    @SerializedName("contactNo") var contactNo: String? = null,
    @SerializedName("address") var address: String? = null
)