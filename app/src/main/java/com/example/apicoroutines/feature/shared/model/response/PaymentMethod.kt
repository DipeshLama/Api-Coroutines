package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName


data class PaymentMethod(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("default") var default: Boolean? = null,
    @SerializedName("icon") var icon: String? = null,
)