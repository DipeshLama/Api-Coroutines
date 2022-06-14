package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class PushNotification(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("sendTime") var sendTime: String? = null,
    @SerializedName("typeId") var typeId: String? = null,
)