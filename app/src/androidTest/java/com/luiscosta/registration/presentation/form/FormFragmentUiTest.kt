package com.luiscosta.registration.presentation.form

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.repository.FakeUserRepository
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormFragmentUiTest {

    @get:Rule
    var activityRule = ActivityTestRule(FormActivity::class.java, true, true)

    @Test
    fun test_View_successful_created() {
        hideKeyboard()
        checkErrorsHidden()
        checkTitles()
        checkEmptyEditTexts()
        checkButton()
        clickButton()
        checkErrorsShown()
        fillInEditTexts("Luis", "luis@ab.cd", "1984-04-22")
        hideKeyboard()
        clickButton()
        checkConfirmationActivityShown()
    }

    private fun checkConfirmationActivityShown() {
        onView(withId(R.id.confirmation_notice_tv)).check(
            matches(isDisplayed())
        )
    }

    private fun checkButton() {
        onView(withId(R.id.register_bt)).check(
            matches(
                withText(R.string.register)
            )
        )
    }

    private fun clickButton() {
        onView(withId(R.id.register_bt)).perform(
            click()
        )
    }

    private fun checkErrorsHidden() {
        onView(withId(R.id.name_error_tv)).check(
            matches(
                not(isDisplayed())
            )
        )
        onView(withId(R.id.email_error_tv)).check(
            matches(
                not(isDisplayed())
            )
        )
        onView(withId(R.id.birth_date_error_tv)).check(
            matches(
                not(isDisplayed())
            )
        )
    }

    private fun checkErrorsShown() {
        onView(withId(R.id.name_error_tv)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.email_error_tv)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.birth_date_error_tv)).check(
            matches(
                isDisplayed()
            )
        )
    }

    private fun checkTitles() {
        onView(withId(R.id.name_title_tv)).check(
            matches(
                withText(R.string.name_title)
            )
        )
        onView(withId(R.id.email_title_tv)).check(
            matches(
                withText(R.string.email_title)
            )
        )
        onView(withId(R.id.birth_date_title_tv)).check(
            matches(
                withText(R.string.date_of_birth_title)
            )
        )
    }

    @Suppress("SameParameterValue")
    private fun fillInEditTexts(name: String, email: String, birthDate: String) {
        FakeUserRepository.fakeUser = UserDomain(name, email, birthDate)
        onView(withId(R.id.name_et)).perform(typeText(name))
        onView(withId(R.id.email_et)).perform(typeText(email))
        onView(withId(R.id.birth_date_et)).perform(typeText(birthDate))
    }

    private fun checkEmptyEditTexts() {
        onView(withId(R.id.name_et)).check(
            matches(
                withHint(R.string.name_hint)
            )
        ).check(
            matches(
                withText("")
            )
        )
        onView(withId(R.id.email_et)).check(
            matches(
                withHint(R.string.email_hint)
            )
        ).check(
            matches(
                withText("")
            )
        )
        onView(withId(R.id.birth_date_et)).check(
            matches(
                withHint(R.string.date_of_birth_hint)
            )
        ).check(
            matches(
                withText("")
            )
        )
    }

    private fun hideKeyboard() {
        onView(withId(R.id.name_et)).perform(closeSoftKeyboard())
        onView(withId(R.id.email_et)).perform(closeSoftKeyboard())
        onView(withId(R.id.birth_date_et)).perform(closeSoftKeyboard())
    }
}