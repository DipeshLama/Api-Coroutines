package com.example.apicoroutines

import androidx.test.filters.SmallTest
import com.example.apicoroutines.database.AppDatabase
import com.example.apicoroutines.database.HomeDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class MainTest {

    //if there are more than one rule make sure hiltRule runs first. So order is made 0.
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: AppDatabase
    private lateinit var homeDao: HomeDao

    @Before
    fun setUp() {
        hiltRule.inject()
        homeDao = database.getHomeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun show() {

    }
}