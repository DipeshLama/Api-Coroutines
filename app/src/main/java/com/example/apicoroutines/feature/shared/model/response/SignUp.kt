package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUp {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("email")
    val email: String? = null

    @SerializedName("username")
    val username: String? = null

    @SerializedName("firstName")
    val firstName: String? = null

    @SerializedName("lastName")
    val lastName: String? = null

    @SerializedName("verified")
    val verified: Boolean? = null

    @SerializedName("mobileNumber")
    val mobileNumber: String? = null

    @SerializedName("createdAt")
    val createdAt: String? = null

    @SerializedName("updatedAt")
    val updatedAt: String? = null

    @SerializedName("image")
    val image: String? = null

    @SerializedName("total_loyalty_points")
    val totalLoyaltyPoints: Int? = null

    @SerializedName("current_loyalty_points")
    val currentLoyaltyPoints: Int? = null

    @SerializedName("user_level")
    val userLevel: String? = null

    @SerializedName("next_level")
    val nextLevel: String? = null

    @SerializedName("required_points_next")
    val requiredPointsNext: Int? = null
}