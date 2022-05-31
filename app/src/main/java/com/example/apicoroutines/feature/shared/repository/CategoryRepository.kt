package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.feature.shared.base.BaseRepository
import javax.inject.Inject

class CategoryRepository @Inject constructor() : BaseRepository() {
    suspend fun getCategories() =
        apiService.getCategories()
}