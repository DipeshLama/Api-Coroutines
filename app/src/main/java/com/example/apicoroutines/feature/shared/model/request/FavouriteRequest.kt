package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class FavouriteRequest(
    @SerializedName("id")
    var id: Int? = null,
)