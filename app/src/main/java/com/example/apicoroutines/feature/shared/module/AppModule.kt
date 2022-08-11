package com.example.apicoroutines.feature.shared.module

import android.content.Context
import androidx.room.Room
import com.example.apicoroutines.database.AppDatabase
import com.example.apicoroutines.utils.constants.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context, AppDatabase::class.java, DatabaseConstants.databaseName)
            .build()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context) =
        AppDatabase.getAppDatabase(context)
}