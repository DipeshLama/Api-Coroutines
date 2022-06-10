package com.example.apicoroutines.feature.shared.model.response

import com.google.gson.annotations.SerializedName

data class AddressDetail(
    @SerializedName("country") var country: String? = null,
    @SerializedName("provience") var provience: String? = null,
    @SerializedName("district") var district: String? = null,
    @SerializedName("local_government") var localGovernment: String? = null,
    @SerializedName("ward") var ward: String? = null,
    @SerializedName("street_address") var streetAddress: String? = null,
    @SerializedName("formatted_address") var formattedAddress: String? = null,
    @SerializedName("intersection") var intersection: String? = null,
)