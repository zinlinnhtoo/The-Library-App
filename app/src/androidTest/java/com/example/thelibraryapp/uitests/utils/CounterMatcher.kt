package com.example.thelibraryapp.uitests.utils

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun <T> second(matcher: Matcher<T>, itemCount: Int): Matcher<T> {
    var isFirst: Boolean = true
    var n = 1
    return object : BaseMatcher<T>() {
        override fun describeTo(description: Description?) {
            description?.appendText(FIRST_ITEM_DESCRIPTION_Counter)
        }

        override fun matches(item: Any?): Boolean {
            if (isFirst && matcher.matches(item)) {
                if (n == itemCount) {

                    isFirst = false
                    return true
                } else n++

            }
            return false
        }
    }
}

const val FIRST_ITEM_DESCRIPTION_Counter = "Return The First Matching Item"