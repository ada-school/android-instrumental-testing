package org.adaschool.instrumentaltesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Santiago Carrillo
 * 10/06/21.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityBehaviourTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun emailInputValidationTest() {
        onView(withId(R.id.email)).perform(clearText(), typeText("santiagomail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.login_button)).perform(click())
        onView(withId(R.id.email)).check(matches(hasErrorText(TestsUtils.getResourceString(R.string.invalid_input))))
    }

    @Test
    fun passwordInputValidationTest() {
        onView(withId(R.id.email)).perform(clearText(), typeText("santiago@mail.com"))
        onView(withId(R.id.login_button)).perform(click())
        onView(withId(R.id.password)).check(matches(hasErrorText(TestsUtils.getResourceString(R.string.invalid_input))))
    }

    @Test
    fun authenticationFailedThenErrorMessageShown() {
        onView(withId(R.id.email)).perform(clearText(), typeText("santiago@mail.com"))
        onView(withId(R.id.password)).perform(clearText(), typeText("password"))
        closeSoftKeyboard()
        onView(withId(R.id.login_button)).perform(click())
        Thread.sleep(6_000)
        onView(withId(R.id.errorMessage)).check(matches(isDisplayed()))
    }


    @Test
    fun authenticationSuccessfulThenErrorMessageNotShown() {
        onView(withId(R.id.email)).perform(clearText(), typeText("test@mail.com"))
        onView(withId(R.id.password)).perform(clearText(), typeText("password"))
        closeSoftKeyboard()
        onView(withId(R.id.login_button)).perform(click())
        Thread.sleep(6_000)
        onView(withId(R.id.errorMessage)).check(matches(not(isDisplayed())))
    }

    @Test
    fun spinnerShownOnlyDuringAuthentication() {
        onView(withId(R.id.email)).perform(clearText(), typeText("test@mail.com"))
        onView(withId(R.id.password)).perform(clearText(), typeText("password"))
        closeSoftKeyboard()
        onView(withId(R.id.login_button)).perform(click())
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        Thread.sleep(6_000)
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }
}