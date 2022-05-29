package com.example.apicoroutines.feature.shared.base

import com.example.apicoroutines.network.RetrofitHelper

abstract class BaseRepository {
    val apiService = RetrofitHelper.getApiService()

}