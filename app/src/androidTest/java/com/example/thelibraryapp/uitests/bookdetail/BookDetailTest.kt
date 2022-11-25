package com.example.thelibraryapp.uitests.bookdetail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.HomeActivity
import com.example.thelibraryapp.uitests.utils.first
import com.example.thelibraryapp.views.viewholders.BookListViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BookDetailTest {
    private val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun checkBookDetail() {
        onView(first(withId(R.id.rvBookList))).perform(
            RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(3, click())
        )

        onView(withId(R.id.tvBookTitleDetail)).check(matches(withText("DESERT STAR")))
        onView(withId(R.id.tvAuthor)).check(matches(withText("Michael Connelly")))
        onView(withId(R.id.tvBookDetail)).check(matches(withText("Ballard and Bosch bury old resentments as they go after two killers.")))
        onView(withId(R.id.tvRating)).check(matches(ViewMatchers.isDisplayed()))
    }
}