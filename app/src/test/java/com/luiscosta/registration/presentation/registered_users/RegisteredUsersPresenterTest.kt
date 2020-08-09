package com.luiscosta.registration.presentation.registered_users

import com.flextrade.jfixture.annotations.Fixture
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

class RegisteredUsersPresenterTest : BaseUnitTest<RegisteredUsersPresenter>() {

    @Mock
    lateinit var mockView: RegisteredUsersContract.View

    @Mock
    lateinit var mockUserRepository: IUserRepository

    @Fixture
    lateinit var fixtUserDomain: UserDomain

    override fun buildSut() =
        RegisteredUsersPresenter(mockView, mockUserRepository, TrampolineSchedulerProvider())

    @Test
    fun `test getAllUsers success`() {
        val expectedList = listOf(fixtUserDomain)
        whenever(mockUserRepository.getAllUsers()).thenReturn(Single.just(expectedList))

        sut.getAllUsers()

        verify(mockUserRepository).getAllUsers()
        verify(mockView).showUsers(expectedList)

        verifyNoMoreInteractions(mockUserRepository, mockView)
    }

    @Test
    fun `test getAllUsers error`() {
        val expectedError = Throwable()
        whenever(mockUserRepository.getAllUsers()).thenReturn(Single.error(expectedError))

        sut.getAllUsers()

        verify(mockUserRepository).getAllUsers()
        verify(mockView).showErrorDialog(R.string.get_all_users_error)

        verifyNoMoreInteractions(mockUserRepository, mockView)
    }

    @Test
    fun `test removeAllUsers success`() {
        val expectedList = emptyList<UserDomain>()
        whenever(mockUserRepository.removeAllUsers()).thenReturn(Single.just(expectedList))

        sut.removeAllUsers()

        verify(mockUserRepository).removeAllUsers()
        verify(mockView).showUsers(expectedList)

        verifyNoMoreInteractions(mockUserRepository, mockView)
    }

    @Test
    fun `test removeAllUsers error`() {
        val expectedError = Throwable()
        whenever(mockUserRepository.removeAllUsers()).thenReturn(Single.error(expectedError))

        sut.removeAllUsers()

        verify(mockUserRepository).removeAllUsers()
        verify(mockView).showErrorDialog(R.string.remove_all_users_error)

        verifyNoMoreInteractions(mockUserRepository, mockView)
    }
}