package com.aa.slangapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentationTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun shouldShowToolbar() {
        onView(withId(R.id.toolbar))
            .check(matches(isDisplayed()));
    }

    @Test
    fun shouldHaveEditTextForSearch() {
        onView(withId(R.id.editTextSearch))
            .perform(
                ViewActions.typeText(USER_SEARCH),
                ViewActions.closeSoftKeyboard()
            )
    }

    @Test
    fun shouldHaveSearchButton() {
        onView(withId(R.id.buttonSearch))
            .check(matches(ViewMatchers.withText(BUTTON_STRING)))
            .perform(ViewActions.click())
    }

    @Test
    fun shouldShowProgressBarAfterSearchButtonClick() {
        onView(withId(R.id.editTextSearch))
            .perform(
                ViewActions.typeText(USER_SEARCH),
                ViewActions.closeSoftKeyboard()
            )
        onView(withId(R.id.buttonSearch))
            .check(matches(ViewMatchers.withText(BUTTON_STRING)))
            .perform(ViewActions.click())
        // online only
        //onView(withId(R.id.progressBar))
        //    .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowHideProgressBarAndShowRecyclerViewAfterSearchButtonClick() {
        onView(withId(R.id.editTextSearch))
            .perform(
                ViewActions.typeText(USER_SEARCH),
                ViewActions.closeSoftKeyboard()
            )
        onView(withId(R.id.buttonSearch))
            .check(matches(ViewMatchers.withText(BUTTON_STRING)))
            .perform(ViewActions.click())
        // idle
        //onView(withId(R.id.progressBar))
        //   .check(matches(isDisplayed()))
//        onView(withId(R.id.recyclerViewResults))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.progressBar))
//            .check(matches(not(isDisplayed())))
    }

    @Test
    fun shouldShowOrderByButtons() {
        onView(withId(R.id.editTextSearch))
            .perform(
                ViewActions.typeText(USER_SEARCH),
                ViewActions.closeSoftKeyboard()
            )
        onView(withId(R.id.buttonSearch))
            .check(matches(ViewMatchers.withText(BUTTON_STRING)))
            .perform(ViewActions.click())
        onView(withId(R.id.buttonOrderByThumbsDown))
            .check(matches(isDisplayed()))
        onView(withId(R.id.buttonOrderByThumbsUp))
            .check(matches(isDisplayed()))
    }

    companion object {
        const val USER_SEARCH = "Sporty"
        const val BUTTON_STRING = "Search"
    }
}