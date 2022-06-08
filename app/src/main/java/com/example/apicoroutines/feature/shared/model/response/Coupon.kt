package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class Coupon(

    @SerializedName("code") var code: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("taxable") var taxable: String? = null,
)