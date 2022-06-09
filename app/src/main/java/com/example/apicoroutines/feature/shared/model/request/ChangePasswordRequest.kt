package com.example.apicoroutines.feature.shared.model.request

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("new-password")
    var newPassword : String? = null,

    @SerializedName("old-password")
    var oldPassword : String? = null,

    @SerializedName("confirm-password")
    var confirmPassword : String? = null

)