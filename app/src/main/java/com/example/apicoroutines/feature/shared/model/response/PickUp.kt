package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName


data class PickUp(

    @SerializedName("customerName") var customerName: String? = null,
    @SerializedName("contactNo") var contactNo: String? = null,
    @SerializedName("note") var note: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("addressDetail") var addressDetail: String? = null,

    )