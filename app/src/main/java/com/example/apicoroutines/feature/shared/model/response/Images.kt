package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("imageName")
    var imageName: String? = null,

    @SerializedName("unit_price_id")
    var unitPriceId: String? = null,
)