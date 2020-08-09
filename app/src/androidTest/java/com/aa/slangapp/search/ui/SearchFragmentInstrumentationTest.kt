package com.aa.slangapp.search.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aa.slangapp.MainActivity
import com.aa.slangapp.R
import org.hamcrest.core.IsNot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchFragmentInstrumentationTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun shouldHaveEditTextForSearch() {
        onView(ViewMatchers.withId(R.id.editTextSearch))
            .perform(
                ViewActions.typeText(USER_SEARCH),
                ViewActions.closeSoftKeyboard()
            )
    }

    @Test
    fun shouldHaveSearchButton() {
        onView(ViewMatchers.withId(R.id.buttonSearch))
            .check(ViewAssertions.matches(ViewMatchers.withText(BUTTON_STRING)))
            .perform(ViewActions.click())
    }

    @Test
    fun shouldShowProgressBarAfterSearchButtonClick() {
        onView(ViewMatchers.withId(R.id.progressBar))
            .check(ViewAssertions.matches(IsNot.not(ViewMatchers.isDisplayed())))
        onView(ViewMatchers.withId(R.id.buttonSearch))
            .check(ViewAssertions.matches(ViewMatchers.withText(BUTTON_STRING)))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.progressBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    companion object {
        const val USER_SEARCH = "Sporty"
        const val BUTTON_STRING = "Search"
    }
}

