package com.example.apicoroutines.feature.shared.repository

import com.example.apicoroutines.database.AppDatabase
import com.example.apicoroutines.feature.shared.base.BaseRepository
import com.example.apicoroutines.feature.shared.model.response.Home
import javax.inject.Inject

class HomeRepository
@Inject
constructor
    (private val appDatabase: AppDatabase) : BaseRepository() {

    suspend fun getHomeScreenData() =
        apiService.getHomeScreenData(1)

    suspend fun saveHomeScreenDataToDb(list: List<Home>) =
        appDatabase.getHomeDao().insert(list)

    fun getHomeScreenDataFromRoom() =
        appDatabase.getHomeDao().retrieve()
}