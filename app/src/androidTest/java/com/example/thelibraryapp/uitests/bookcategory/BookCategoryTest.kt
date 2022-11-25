package com.example.thelibraryapp.uitests.bookcategory

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.HomeActivity
import com.example.thelibraryapp.uitests.utils.first
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BookCategoryTest {

    private val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun categoryBookConfirm() {
        onView(first(withId(R.id.llCategoryTitle))).perform(ViewActions.click())
        Thread.sleep(2000L)
        onView(withId(R.id.tvTitleInCategory)).check(matches(ViewMatchers.withText("Hardcover Fiction")))
        onView(first(withId(R.id.tvBookTitleInCategory))).check(matches(ViewMatchers.withText("THE BOYS FROM BILOXI")))
        onView(ViewMatchers.withText("THE BOYS FROM BILOXI")).check(matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("THE BOYS FROM BILOXI")).perform(ViewActions.click())
        onView(withId(R.id.tvBookTitleDetail)).check(matches(ViewMatchers.withText("THE BOYS FROM BILOXI")))
    }
}