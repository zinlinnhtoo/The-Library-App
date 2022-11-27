package com.example.thelibraryapp.uitests.shelfscreen

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.thelibraryapp.R
import com.example.thelibraryapp.activities.HomeActivity
import com.example.thelibraryapp.uitests.utils.first
import com.example.thelibraryapp.views.viewholders.ShelfViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ShelfScreenTest {
    private val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun goToShelfScreen() {
        onView(withId(R.id.action_library)).perform(click())
        onView(ViewMatchers.withText("Your shelves")).perform(click())

        onView(withId(R.id.tvCreateShelf)).perform(click())
        onView(withId(R.id.etShelfName)).perform(
            ViewActions.typeText(
                "New Shelf"
            ), ViewActions.pressImeActionButton()
        )
        onView(ViewMatchers.withText("New Shelf")).check(
            matches(
                ViewMatchers.isDisplayed()
            )
        )

        onView(ViewMatchers.withText("Your books")).perform(click())
        onView(first<View>(withId(R.id.ivOptionSmallGrid))).perform(click())
        onView(withId(R.id.llAddToShelves)).perform(click())
        onView(first<View>(withId(R.id.cbAddToShelf))).perform(click())
        onView(withId(R.id.btnAddToShelves)).perform(click())
        onView(ViewMatchers.withText("Your shelves")).perform(click())
        onView(first<View>(withId(R.id.tvBookCount))).check(
            matches(
                ViewMatchers.withText("1 books")
            )
        )

        onView(withId(R.id.rvShelf)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.ivBtnOption)).perform(click())
        Thread.sleep(1000L)
        onView(withId(R.id.tvRenameShelf)).perform(click())
        onView(withId(R.id.etShelfName)).perform(
            ViewActions.typeText(
                "Renamed Shelf"
            ), ViewActions.pressImeActionButton()
        )
//        onView(withId(R.id.ivBtnBack)).perform(click())
        onView(first<View>(withId(R.id.tvShelfTitle))).check(matches(
            ViewMatchers.withText(
                "Renamed Shelf"
            )
        ))
        onView(withId(R.id.rvShelf)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.ivBtnOption)).perform(click())
        Thread.sleep(1000L)
        onView(withId(R.id.tvDeleteShelf)).perform(click())
        onView(withId(R.id.rvShelf)).check(matches(ViewMatchers.hasChildCount(0)))
    }

//    @Test
//    fun createShelf() {
//        goToShelfScreen()
//
//    }

//    @Test
//    fun addToShelf() {
//        onView(withId(R.id.action_library)).perform(click())
//
//    }

//    @Test
//    fun renameShelf() {
//        goToShelfScreen()
//
//    }

//    @Test
//    fun deleteShelf() {
//        goToShelfScreen()
//
//    }
}