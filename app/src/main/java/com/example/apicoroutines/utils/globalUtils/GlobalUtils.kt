package com.example.apicoroutines.utils.globalUtils

import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.RequestBody

object GlobalUtils {
    fun buildGson(any : Any) : RequestBody {
        val builder = GsonBuilder()
        val gson = builder.create()
        val json = gson.toJson(any)
        return RequestBody.create(MediaType.parse("application/json"), json)
    }
}