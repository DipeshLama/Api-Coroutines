package com.example.apicoroutines.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.utils.constants.DatabaseConstants

@Dao
interface HomeDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(home : List<Home>)

    @Query("SELECT * FROM ${DatabaseConstants.home}")
    fun retrieve () : LiveData<List<Home>>
}