package com.example.apicoroutines.feature.shared.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Category() : Parcelable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("parentId")
    var parentId: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("icon")
    var icon: String? = null

    @SerializedName("backgroundImage")
    var backgroundImage: String? = null

    @SerializedName("position")
    var position: Int? = null

    @SerializedName("hasProduct")
    var hasProduct: Boolean? = null

    @SerializedName("avgRating")
    var avgRating: Int? = null

    @SerializedName("ratingCount")
    var ratingCount: Int? = null

    @SerializedName("productCount")
    var productCount: Int? = null

    @SerializedName("userRating")
    var userRating: String? = null

    @SerializedName("banner")
    var banner: ArrayList<String> = arrayListOf()

    @SerializedName("hierarchy_level")
    var hierarchyLevel: Int? = null

    @SerializedName("isRestaurant")
    var isRestaurant: Boolean? = null

    @SerializedName("isRestaurantOpen")
    var isRestaurantOpen: Boolean? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        parentId = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        slug = parcel.readString()
        icon = parcel.readString()
        backgroundImage = parcel.readString()
        position = parcel.readValue(Int::class.java.classLoader) as? Int
        hasProduct = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        avgRating = parcel.readValue(Int::class.java.classLoader) as? Int
        ratingCount = parcel.readValue(Int::class.java.classLoader) as? Int
        productCount = parcel.readValue(Int::class.java.classLoader) as? Int
        userRating = parcel.readString()
        hierarchyLevel = parcel.readValue(Int::class.java.classLoader) as? Int
        isRestaurant = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isRestaurantOpen = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(parentId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(slug)
        parcel.writeString(icon)
        parcel.writeString(backgroundImage)
        parcel.writeValue(position)
        parcel.writeValue(hasProduct)
        parcel.writeValue(avgRating)
        parcel.writeValue(ratingCount)
        parcel.writeValue(productCount)
        parcel.writeString(userRating)
        parcel.writeValue(hierarchyLevel)
        parcel.writeValue(isRestaurant)
        parcel.writeValue(isRestaurantOpen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}