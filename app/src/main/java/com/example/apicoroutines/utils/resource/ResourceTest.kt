package com.example.apicoroutines.utils.resource

sealed class ResourceTest<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : ResourceTest<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceTest<T>(data, message)
    class Loading<T> : ResourceTest<T>()
}