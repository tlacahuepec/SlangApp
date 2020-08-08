package com.aa.slangapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun shouldHaveEditTextForSearch() {
        onView(withId(R.id.editTextSearch))
            .perform(typeText(USER_SEARCH), closeSoftKeyboard())
    }

    @Test
    fun shouldHaveSearchButton() {
        onView(withId(R.id.buttonSearch))
            .check(matches(withText(BUTTON_STRING)))
            .perform(click())
    }

    companion object {
        const val USER_SEARCH = "Sporty"
        const val BUTTON_STRING = "Search"
    }
}