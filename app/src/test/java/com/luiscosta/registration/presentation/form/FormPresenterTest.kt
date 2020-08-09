package com.luiscosta.registration.presentation.form

import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.schedulers.TrampolineSchedulerProvider
import com.luiscosta.registration.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junitparams.JUnitParamsRunner
import junitparams.NamedParameters
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

const val FIELDS = "FIELDS"

@RunWith(JUnitParamsRunner::class)
class FormPresenterTest : BaseUnitTest<FormPresenter>() {

    @Mock
    lateinit var mockView: FormContract.View

    @Mock
    lateinit var mockUserRepository: IUserRepository

    private val expectedId = 1L

    override fun buildSut() =
        FormPresenter(mockView, mockUserRepository, TrampolineSchedulerProvider())

    @Test
    fun `test clearAllData`() {
        sut.clearAllData()

        verify(mockView).clearName()
        verify(mockView).clearEmail()
        verify(mockView).clearBirthDate()
        verify(mockView).enableRegisterButton(true)

        verifyNoMoreInteractions(mockView)
        verifyZeroInteractions(mockUserRepository)
    }

    @Test
    @Parameters(named = FIELDS)
    fun `test saveUseData`(
        name: String,
        email: String,
        birthDate: String,
        expectedNameValidity: Boolean,
        expectedEmailValidity: Boolean,
        expectedBirthDateValidity: Boolean,
        expectedRepositorySuccess: Boolean,
        expectedRepositoryResponse: Single<Long>
    ) {
        val expectedUser = UserDomain(name, email, birthDate)
        val reachesRepository =
            expectedNameValidity && expectedEmailValidity && expectedBirthDateValidity

        val empty = name.isEmpty() || email.isEmpty() || birthDate.isEmpty()

        whenever(mockUserRepository.addUser(expectedUser)).thenReturn(expectedRepositoryResponse)

        sut.saveUserData(name, email, birthDate)

        if (empty) {
            verify(mockView).showToast(R.string.fill_all_fields)
        }

        if (reachesRepository) {
            verify(mockUserRepository).addUser(expectedUser)
            if (expectedRepositorySuccess) {
                verify(mockView).showConfirmationScreen(expectedId)
            } else {
                verify(mockView).showErrorDialog(R.string.add_user_error)
            }
        }

        verify(mockView).enableRegisterButton(false)
        verify(mockView).showNameError(!expectedNameValidity)
        verify(mockView).showEmailError(!expectedEmailValidity)
        verify(mockView).showBirthDateError(!expectedBirthDateValidity)

        verifyNoMoreInteractions(mockView, mockUserRepository)
    }

    @NamedParameters(FIELDS)
    private fun params(): Array<Array<Any>> = arrayOf(
        arrayOf("", "", "", false, false, false, true, Single.just(expectedId)),
        arrayOf("L", "", "", true, false, false, true, Single.just(expectedId)),
        arrayOf("L", "l@ab.c", "1991-03-15", true, false, true, true, Single.just(expectedId)),
        arrayOf("L", "@a.bc", "1991-03-15", true, false, true, true, Single.just(expectedId)),
        arrayOf("L", "l@a.bc.d", "1991-03-15", true, false, true, true, Single.just(expectedId)),
        arrayOf("L", "l.@a.bc", "1991-03-15", true, false, true, true, Single.just(expectedId)),
        arrayOf("L", ".l@a.bc", "1991-03-15", true, false, true, true, Single.just(expectedId)),
        arrayOf("L", "l@c.ab", "", true, true, false, true, Single.just(expectedId)),
        arrayOf("L", "l@c.ab", "1991-03-15", true, true, true, true, Single.just(expectedId)),
        arrayOf("L", "l@cba.bx.ab", "1991-03-15", true, true, true, true, Single.just(expectedId)),
        arrayOf(
            "L", "l@c.ab", "1991-03-15", true, true, true, false, Single.error<Long>(Throwable())
        )
    )
}