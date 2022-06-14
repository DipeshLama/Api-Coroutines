package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SectionDetails {
    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("category_search")
    @Expose
    val categorySearch: String? = null

    @SerializedName("design_type")
    @Expose
    val designType: String? = null

    @SerializedName("collection_type")
    @Expose
    val collectionType: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("start_date")
    @Expose
    val startDate: String? = null

    @SerializedName("end_date")
    @Expose
    val endDate: String? = null

    @SerializedName("products")
    @Expose
    val products: List<Product>? = null

    @SerializedName("tags")
    @Expose
    val tags: List<Tags>? = null
}