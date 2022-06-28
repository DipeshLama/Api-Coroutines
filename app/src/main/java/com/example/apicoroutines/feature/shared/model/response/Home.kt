package com.example.apicoroutines.feature.shared.model.response

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apicoroutines.utils.constants.DatabaseConstants
import com.google.gson.annotations.SerializedName

@Entity(tableName = DatabaseConstants.home)
class Home() : Parcelable {
    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("status")
    var status: Boolean? = null

    @SerializedName("position")
    var position: Int? = null

    @SerializedName("details")
    var details: List<Details>? = null

    @SerializedName("sectionDetails")
    var sectionDetails: SectionDetails? = null

    @SerializedName("categories")
    var categories: List<Category>? = null

    var viewType: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        status = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        position = parcel.readValue(Int::class.java.classLoader) as? Int
        details = parcel.createTypedArrayList(Details)
        sectionDetails = parcel.readParcelable(SectionDetails::class.java.classLoader)
        categories = parcel.createTypedArrayList(Category)
        viewType = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeValue(status)
        parcel.writeValue(position)
        parcel.writeTypedList(details)
        parcel.writeParcelable(sectionDetails, flags)
        parcel.writeTypedList(categories)
        parcel.writeString(viewType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Home> {
        override fun createFromParcel(parcel: Parcel): Home {
            return Home(parcel)
        }

        override fun newArray(size: Int): Array<Home?> {
            return arrayOfNulls(size)
        }
    }
}