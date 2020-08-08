package com.aa.slangapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.core.IsNot.not
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

    @Test
    fun shouldShowProgressBarAfterSearchButtonClick() {
        onView(withId(R.id.progressBar))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.buttonSearch))
            .check(matches(withText(BUTTON_STRING)))
            .perform(click())
        // TODO: assert after view model addition
        // onView(withId(R.id.progressBar))
        //   .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowToolbar() {
        onView(withId(R.id.toolbar))
            .check(matches(isDisplayed()));
    }

    companion object {
        const val USER_SEARCH = "Sporty"
        const val BUTTON_STRING = "Search"
    }
}