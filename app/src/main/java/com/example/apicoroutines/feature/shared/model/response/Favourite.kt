package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

class Favourite {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("storeUserId")
    var storeUserId: Int? = null

    @SerializedName("position")
    var position : Int? = null

    @SerializedName("product")
    var product : Product? =null
}