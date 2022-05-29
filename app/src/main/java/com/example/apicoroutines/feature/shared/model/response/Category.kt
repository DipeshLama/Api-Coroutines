package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("parentId")
    var parentId: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("slug")
    var slug: String? = null,

    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("backgroundImage")
    var backgroundImage: String? = null,

    @SerializedName("position")
    var position: Int? = null,

    @SerializedName("hasProduct")
    var hasProduct: Boolean? = null,

    @SerializedName("avgRating")
    var avgRating: Int? = null,

    @SerializedName("ratingCount")
    var ratingCount: Int? = null,

    @SerializedName("productCount")
    var productCount: Int? = null,

    @SerializedName("userRating")
    var userRating: String? = null,

    @SerializedName("banner")
    var banner: ArrayList<String> = arrayListOf(),

    @SerializedName("hierarchy_level")
    var hierarchyLevel: Int? = null,

    @SerializedName("isRestaurant")
    var isRestaurant: Boolean? = null,

    @SerializedName("isRestaurantOpen")
    var isRestaurantOpen: Boolean? = null
)