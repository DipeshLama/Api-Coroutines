package com.example.apicoroutines.feature.shared.base

import com.example.apicoroutines.network.RetrofitHelper
import com.example.apicoroutines.utils.globalUtils.GlobalUtils

abstract class BaseRepository {
    val apiService = RetrofitHelper.getApiService()

    fun getGlobalUtils(any: Any) =
        GlobalUtils.buildGson(any)
}