package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Details {
    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("category_search")
    @Expose
    val categorySearch: String? = null

    @SerializedName("web_link")
    @Expose
    val webLink: String? = null

    @SerializedName("link_to")
    @Expose
    val linkTo: String? = null

    @SerializedName("status")
    @Expose
    val status: Boolean? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("start_date")
    @Expose
    val startDate: String? = null

    @SerializedName("end_date")
    @Expose
    val endDate: String? = null

    @SerializedName("method_type")
    @Expose
    val methodType: String? = null

    @SerializedName("subsection_exist")
    @Expose
    val subsectionExist: String? = null

    @SerializedName("images")
    @Expose
    val images: String? = null

    @SerializedName("category")
    @Expose
    val category: Category? = null

    @SerializedName("product")
    @Expose
    val product: Int? = null

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("position")
    @Expose
    val position: Int? = null
}