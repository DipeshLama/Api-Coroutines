package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tags {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("content")
    @Expose
    val content: String? = null

    @SerializedName("imageLink")
    @Expose
    val imageLink: String? = null

    @SerializedName("featured")
    @Expose
    val featured: Boolean? = null

    @SerializedName("slug")
    @Expose
    val slug: String? = null
}