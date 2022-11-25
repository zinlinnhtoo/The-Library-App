package com.example.thelibraryapp.uitests.booksearch

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.HomeActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BookSearchTest {
    private val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun checkSearchBook() {
        onView(withId(R.id.rlSearchBar)).perform(click())
        onView(withId(R.id.etSearch)).perform(TypeTextAction("Avenger"), closeSoftKeyboard())
        Thread.sleep(2000L)
        onView(withText("Frederick Forsyth")).check(matches(isDisplayed()))
        onView(withText("Frederick Forsyth")).perform(click())
        onView(withId(R.id.tvBookTitleDetail)).check(matches(withText("Avenger")))
    }
}