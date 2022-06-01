package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Extra {
    @SerializedName("title")
    val title: String? = null

    @SerializedName("value")
    val value: Int? = null
}