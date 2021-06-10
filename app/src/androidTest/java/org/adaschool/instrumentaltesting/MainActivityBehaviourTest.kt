package org.adaschool.instrumentaltesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
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
}