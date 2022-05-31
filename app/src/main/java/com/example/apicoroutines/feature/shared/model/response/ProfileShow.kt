package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class ProfileShow(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("username")
    var username: String? = null,

    @SerializedName("firstName")
    var firstName: String? = null,

    @SerializedName("lastName")
    var lastName: String? = null,

    @SerializedName("verified")
    var verified: Boolean? = null,

    @SerializedName("mobileNumber")
    var mobileNumber: String? = null,

    @SerializedName("createdAt")
    var createdAt: String? = null,

    @SerializedName("updatedAt")
    var updatedAt: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("total_loyalty_points")
    var totalLoyaltyPoints: Int? = null
)