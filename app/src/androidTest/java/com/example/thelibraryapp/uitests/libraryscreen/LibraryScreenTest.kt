package com.example.thelibraryapp.uitests.libraryscreen

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.HomeActivity
import com.example.thelibraryapp.uitests.utils.first
import com.example.thelibraryapp.views.viewholders.FilterChipViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LibraryScreenTest {

    private val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun goToLibrary() {
        onView(withId(R.id.action_library)).perform(ViewActions.click())
    }

    @Test
    fun checkChangeList() {
        goToLibrary()
        onView(withId(R.id.rvSmallGrid)).check(ViewAssertions.matches(hasChildCount(3)))

        onView(withId(R.id.ivViewAs)).perform(ViewActions.click())
        onView(withId(R.id.rbLargeGrid)).perform(ViewActions.click())
        onView(withId(R.id.rvLargeGrid)).check(ViewAssertions.matches(isDisplayed()))

        onView(withId(R.id.ivViewAs)).perform(ViewActions.click())
        onView(withId(R.id.rbList)).perform(ViewActions.click())
        onView(withId(R.id.rvListBook)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkSortedList() {
        goToLibrary()
        onView(withId(R.id.ivSort)).perform(ViewActions.click())
        onView(withId(R.id.rbTitle)).perform(ViewActions.click())
        onView(first(withId(R.id.tvBookTitleSmallGrid))).check(
            ViewAssertions.matches(
                withText(
                    "ALL ABOUT LOVE"
                )
            )
        )

        onView(withId(R.id.ivSort)).perform(ViewActions.click())
        onView(withId(R.id.rbAuthor)).perform(ViewActions.click())
        onView(first(withId(R.id.tvBookTitleSmallGrid))).check(
            ViewAssertions.matches(
                withText(
                    "THE BOYS FROM BILOXI"
                )
            )
        )

        onView(withId(R.id.ivSort)).perform(ViewActions.click())
        onView(withId(R.id.rbRecent)).perform(ViewActions.click())
        onView(first(withId(R.id.tvBookTitleSmallGrid))).check(
            ViewAssertions.matches(
                withText(
                    "THE BOYS FROM BILOXI"
                )
            )
        )
    }

    @Test
    fun checkFilter() {
        goToLibrary()
        onView(withId(R.id.rvChip)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FilterChipViewHolder>(
                0, ViewActions.click()
            )
        )
        onView(first(withId(R.id.tvBookTitleSmallGrid))).check(
            ViewAssertions.matches(
                withText(
                    "THE BOYS FROM BILOXI"
                )
            )
        )
        onView(withId(R.id.btnClearFilter)).perform(ViewActions.click())

        onView(withId(R.id.rvChip)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FilterChipViewHolder>(
                1, ViewActions.click()
            )
        )
        onView(first(withId(R.id.tvBookTitleSmallGrid))).check(
            ViewAssertions.matches(
                withText(
                    "SO HELP ME GOD"
                )
            )
        )
        onView(withId(R.id.btnClearFilter)).perform(ViewActions.click())

        onView(withId(R.id.rvChip)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FilterChipViewHolder>(
                2, ViewActions.click()
            )
        )
        onView(first(withId(R.id.tvBookTitleSmallGrid))).check(
            ViewAssertions.matches(
                withText(
                    "ALL ABOUT LOVE"
                )
            )
        )
        onView(withId(R.id.btnClearFilter)).perform(ViewActions.click())
    }
}