package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class StatusLog(

    @SerializedName("position") var position: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("iconUrl") var iconUrl: String? = null,
    @SerializedName("title") var title: String? = null
)