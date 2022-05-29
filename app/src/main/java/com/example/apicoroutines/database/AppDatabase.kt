package com.example.apicoroutines.database

import android.content.Context
import androidx.room.*
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.utils.constants.DatabaseConstants

@Database(
    entities = [Home::class],
    version = 1
)
@TypeConverters(HomeTypeConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getHomeDao(): HomeDao

    companion object {
        private var instance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        DatabaseConstants.databaseName
                    ).build()
                }
            }
            return instance!!
        }
    }
}