package com.example.bingtrading

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.bingtrading", appContext.packageName)

        Espresso.onView(ViewMatchers.withText("LOGIN"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.edittext_email))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("victorsiig@live.dk"))
        Espresso.onView(withId(R.id.edittext_password))
            .perform(ViewActions.clearText())
            .perform(ViewActions.typeText("hej123"))
            .perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.button_login)).perform(ViewActions.click())
        pause(2000) // to wait for Firebase response to arrive
        Espresso.onView(withId(R.id.button_sort))
            .check(ViewAssertions.matches(ViewMatchers.withText(("SORT"))))
    }

    private fun pause(millis: Long) {
        try {
            Thread.sleep(millis)
            // https://www.repeato.app/android-espresso-why-to-avoid-thread-sleep/
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
