package com.example.apicoroutines.utils.helper

import com.example.apicoroutines.feature.shared.model.response.ErrorResponse
import com.google.gson.Gson

fun String?.getError(): String {
    val gson = Gson()
    val root = gson.fromJson(this, ErrorResponse::class.java)
    val errorMessages = root?.errors

    return if (!errorMessages.isNullOrEmpty()) {
        errorMessages[0]?.message ?: "Some Error Occurred"
    } else
        "Default error"
}
