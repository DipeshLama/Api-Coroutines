package com.example.apicoroutines.feature.shared.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Details() : Parcelable {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("category_search")
    var categorySearch: String? = null

    @SerializedName("web_link")
    var webLink: String? = null

    @SerializedName("link_to")
    var linkTo: String? = null

    @SerializedName("status")
    var status: Boolean? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("start_date")
    var startDate: String? = null

    @SerializedName("end_date")
    var endDate: String? = null

    @SerializedName("method_type")
    var methodType: String? = null

    @SerializedName("subsection_exist")
    var subsectionExist: String? = null

    @SerializedName("images")
    var images: String? = null

    @SerializedName("category")
    var category: Category? = null

    @SerializedName("product")
    var product: Int? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("position")
    var position: Int? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        categorySearch = parcel.readString()
        webLink = parcel.readString()
        linkTo = parcel.readString()
        status = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        description = parcel.readString()
        startDate = parcel.readString()
        endDate = parcel.readString()
        methodType = parcel.readString()
        subsectionExist = parcel.readString()
        images = parcel.readString()
        category = parcel.readParcelable(Category::class.java.classLoader)
        product = parcel.readValue(Int::class.java.classLoader) as? Int
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        position = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(categorySearch)
        parcel.writeString(webLink)
        parcel.writeString(linkTo)
        parcel.writeValue(status)
        parcel.writeString(description)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(methodType)
        parcel.writeString(subsectionExist)
        parcel.writeString(images)
        parcel.writeParcelable(category, flags)
        parcel.writeValue(product)
        parcel.writeValue(id)
        parcel.writeValue(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Details> {
        override fun createFromParcel(parcel: Parcel): Details {
            return Details(parcel)
        }

        override fun newArray(size: Int): Array<Details?> {
            return arrayOfNulls(size)
        }
    }
}