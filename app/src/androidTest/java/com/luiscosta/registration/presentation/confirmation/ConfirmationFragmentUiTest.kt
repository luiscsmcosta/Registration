package com.luiscosta.registration.presentation.confirmation

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.repository.FakeUserRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfirmationFragmentUiTest {

    @get:Rule
    var activityRule = ActivityTestRule(ConfirmationActivity::class.java, true, false)

    private val appResources = ApplicationProvider.getApplicationContext<Context>().resources

    private val expectedName = "L"
    private val expectedEmail = "luis@ab.abc.ba"
    private val expectedBirthDate = "1989-01-01"

    @Before
    fun setup() {
        FakeUserRepository.fakeUser = UserDomain(expectedName, expectedEmail, expectedBirthDate)

        activityRule.launchActivity(null)
    }

    @Test
    fun test_View_successful_created() {

        onView(withId(R.id.confirmation_notice_tv)).check(
            matches(
                withText(R.string.confirmation_notice)
            )
        )
        onView(withId(R.id.name_tv)).check(
            matches(
                withText(
                    appResources.getString(
                        R.string.confirmation_name,
                        expectedName
                    )
                )
            )
        )
        onView(withId(R.id.email_tv)).check(
            matches(
                withText(
                    appResources.getString(
                        R.string.confirmation_email,
                        expectedEmail
                    )
                )
            )
        )
        onView(withId(R.id.birth_date_tv)).check(
            matches(
                withText(
                    appResources.getString(
                        R.string.confirmation_birth_date,
                        expectedBirthDate
                    )
                )
            )
        )
        onView(withId(R.id.registered_users_tv)).check(
            matches(
                withText(
                    appResources.getString(R.string.registered_users)
                )
            )
        )
    }
}