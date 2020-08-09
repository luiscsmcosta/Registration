package com.luiscosta.registration.presentation.confirmation

import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.schedulers.TrampolineSchedulerProvider
import com.luiscosta.registration.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock

class ConfirmationPresenterTest : BaseUnitTest<ConfirmationPresenter>() {

    @Mock
    lateinit var mockView: ConfirmationContract.View

    @Mock
    lateinit var mockUserRepository: IUserRepository

    override fun buildSut() =
        ConfirmationPresenter(mockView, mockUserRepository, TrampolineSchedulerProvider())

    @Test
    fun `test getUser success`() {
        val fixtUserDomain: UserDomain = fixture.create(UserDomain::class.java)

        val id = 1L

        whenever(mockUserRepository.getUser(id)).thenReturn(Single.just(fixtUserDomain))

        sut.getUser(id)

        verify(mockUserRepository).getUser(id)
        verify(mockView).showName(fixtUserDomain.name)
        verify(mockView).showEmail(fixtUserDomain.email)
        verify(mockView).showBirthDate(fixtUserDomain.birthDate)

        verifyNoMoreInteractions(mockView, mockUserRepository)
    }

    @Test
    fun `test getUser error`() {
        val id = 1L

        whenever(mockUserRepository.getUser(id)).thenReturn(Single.error(Throwable()))

        sut.getUser(id)

        verify(mockUserRepository).getUser(id)
        verify(mockView).showErrorDialog(R.string.get_user_error)

        verifyNoMoreInteractions(mockView, mockUserRepository)
    }
}