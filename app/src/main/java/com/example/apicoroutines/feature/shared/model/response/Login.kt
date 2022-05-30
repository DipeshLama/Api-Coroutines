package com.example.apicoroutines.feature.shared.model.response

import androidx.room.Entity
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.constants.DatabaseConstants
import com.google.gson.annotations.SerializedName

@Entity(tableName = DatabaseConstants.user)
class Login {

    @SerializedName("token_type")
    val tokenType: String? = null

    @SerializedName("expires_in")
    val expiresIn: Int? = null

    @SerializedName("access_token")
    val accessToken: String? = null

    @SerializedName("refresh_token")
    val refreshToken: String? = null

    @SerializedName("warehouse_id")
    val warehouseId: Int? = null

    @SerializedName("user_verified")
    val userVerified: Boolean? = null
}