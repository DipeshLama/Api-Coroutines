package com.example.apicoroutines.feature.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.apicoroutines.R
import com.example.apicoroutines.feature.login.LoginFragment
import com.example.apicoroutines.feature.login.LoginFragmentDirections
import com.example.apicoroutines.feature.main.AuthActivity
import com.example.apicoroutines.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    @get : Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun clickProduct_navigateToProductDetail() {
        val navController = mock(NavController::class.java)
//        val scenario = ActivityScenario.launch(AuthActivity::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.txvForgetPassword)).perform(click())

        verify(navController).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        
        onView(withId(R.id.txvEnterEmailTitle)).check(matches(isDisplayed()))
    }
    
}