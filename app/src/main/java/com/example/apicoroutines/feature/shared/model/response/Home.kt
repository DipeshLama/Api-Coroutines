package com.example.apicoroutines.feature.shared.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apicoroutines.utils.constants.DatabaseConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = DatabaseConstants.home)
data class Home (

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("status")
    @Expose
    val status: Boolean? = null,

    @SerializedName("position")
    @Expose
    val position: Int? = null,

    @SerializedName("details")
    @Expose
    val details: List<Details>? = null,

    @SerializedName("sectionDetails")
    @Expose
    val sectionDetails: SectionDetails? = null,

    @SerializedName("categories")
    @Expose
    val categories: List<Category>? = null,

    var viewType : String? = null
)