package com.example.thelibraryapp.uitests.homescreen

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.HomeActivity
import com.example.thelibraryapp.uitests.utils.first
import com.example.thelibraryapp.uitests.utils.second
import com.example.thelibraryapp.views.viewholders.BookCategoryViewHolder
import com.example.thelibraryapp.views.viewholders.BookListViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeScreenTest {
    private val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun carouselViewEmptyAndInitialTest() {
        onView(withId(R.id.rvBannerBook)).check(matches(hasChildCount(0)))
        onView(first<View>(withText("THE BOYS FROM BILOXI"))).check(matches(isDisplayed()))
    }

    @Test
    fun scrollBookListAndClick() {

        onView(first(withId(R.id.rvBookList))).perform(
            RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(
                0, click()
            )
        )

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(first(withId(R.id.rvBookCategory))).perform(
            RecyclerViewActions.actionOnItemAtPosition<BookCategoryViewHolder>(
                2, scrollTo()
            )
        )

        onView(second(withId(R.id.rvBookList), 2)).perform(
            RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(
                1, click()
            )
        )

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(first(withId(R.id.rvBookCategory))).perform(
            RecyclerViewActions.actionOnItemAtPosition<BookCategoryViewHolder>(
                3, scrollTo()
            )
        )

        onView(second(withId(R.id.rvBookList), 3)).perform(
            RecyclerViewActions.actionOnItemAtPosition<BookListViewHolder>(
                2, click()
            )
        )
    }

    @Test
    fun checkCarouselAfterTapBook() {
        onView(withId(R.id.rvBannerBook)).check(matches(hasChildCount(2)))
    }

    @Test
    fun goToLibraryAndCheckBookList() {
        onView(withId(R.id.action_library)).perform(click())
        onView(withId(R.id.rvSmallGrid)).check(matches(hasChildCount(3)))
    }
}