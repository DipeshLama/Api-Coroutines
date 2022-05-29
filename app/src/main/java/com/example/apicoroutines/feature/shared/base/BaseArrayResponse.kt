package com.example.apicoroutines.feature.shared.base

import com.google.gson.annotations.SerializedName

class BaseArrayResponse <T>{
    @SerializedName("data")
    val data :List<T>?= null
}