package com.example.apicoroutines.feature.shared.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SectionDetails() : Parcelable {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("category_search")
    var categorySearch: String? = null

    @SerializedName("design_type")
    var designType: String? = null

    @SerializedName("collection_type")
    var collectionType: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("start_date")
    var startDate: String? = null

    @SerializedName("end_date")
    var endDate: String? = null

    @SerializedName("products")
    var products: List<Product>? = null

    @SerializedName("tags")
    var tags: List<Tags>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        categorySearch = parcel.readString()
        designType = parcel.readString()
        collectionType = parcel.readString()
        description = parcel.readString()
        startDate = parcel.readString()
        endDate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(categorySearch)
        parcel.writeString(designType)
        parcel.writeString(collectionType)
        parcel.writeString(description)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SectionDetails> {
        override fun createFromParcel(parcel: Parcel): SectionDetails {
            return SectionDetails(parcel)
        }

        override fun newArray(size: Int): Array<SectionDetails?> {
            return arrayOfNulls(size)
        }
    }
}