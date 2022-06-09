package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class PaymentOptions(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("default")
    var default: Boolean? = null,

    @SerializedName("icon")
    var icon: String? = null,

    @SerializedName("live")
    var live: Boolean? = null,

    @SerializedName("merchantCode")
    var merchantCode: String? = null,

    @SerializedName("merchantId")
    var merchantId: String? = null,

    @SerializedName("merchantSecret")
    var merchantSecret: String? = null,

    var isSelected : Boolean = false
)