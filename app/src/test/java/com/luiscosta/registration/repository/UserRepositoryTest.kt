package com.luiscosta.registration.repository

import com.flextrade.jfixture.annotations.Fixture
import com.luiscosta.registration.database.dao.UserDao
import com.luiscosta.registration.database.entity.User
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class UserRepositoryTest: BaseUnitTest<UserRepository>() {

    @Mock lateinit var mockUserDao: UserDao

    @Fixture lateinit var fixtUserDomain: UserDomain
    @Fixture lateinit var fixtUser: User

    override fun buildSut() = UserRepository(mockUserDao)

    @Test
    fun `test addUser success`() {
        val expectedId = 1L
        val expectedUser = User(
            name = fixtUserDomain.name,
            email = fixtUserDomain.email,
            birthDate = fixtUserDomain.birthDate
        )

        whenever(mockUserDao.insertUser(expectedUser)).thenReturn(Single.just(expectedId))

        sut.addUser(fixtUserDomain).test().assertValue(expectedId)

        verify(mockUserDao).insertUser(expectedUser)

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test getUser success`() {
        val expectedUserDomain = UserDomain(
            name = fixtUser.name,
            email = fixtUser.email,
            birthDate = fixtUser.birthDate
        )
        val id = 1L

        whenever(mockUserDao.getUserById(id)).thenReturn(Single.just(fixtUser))

        sut.getUser(id).test().assertValue(expectedUserDomain)

        verify(mockUserDao).getUserById(id)

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test getAllUsers success`() {
        val expectedUserList = listOf(
            UserDomain(
                name = fixtUser.name,
                email = fixtUser.email,
                birthDate = fixtUser.birthDate
            )
        )

        whenever(mockUserDao.getAll()).thenReturn(Single.just(listOf(fixtUser)))

        sut.getAllUsers().test().assertValue(expectedUserList)

        verify(mockUserDao).getAll()

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test removeAllUsers success`() {
        whenever(mockUserDao.deleteAll()).thenReturn(Completable.complete())

        sut.removeAllUsers().test().assertValue(emptyList())

        verify(mockUserDao).deleteAll()

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test addUser error`() {
        val expectedUser = User(
            name = fixtUserDomain.name,
            email = fixtUserDomain.email,
            birthDate = fixtUserDomain.birthDate
        )
        val expectedError = Throwable()

        whenever(mockUserDao.insertUser(expectedUser)).thenReturn(Single.error(expectedError))

        sut.addUser(fixtUserDomain).test().assertError(expectedError)

        verify(mockUserDao).insertUser(expectedUser)

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test getUser error`() {
        val id = 1L
        val expectedError = Throwable()

        whenever(mockUserDao.getUserById(id)).thenReturn(Single.error(expectedError))

        sut.getUser(id).test().assertError(expectedError)

        verify(mockUserDao).getUserById(id)

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test getAllUsers error`() {
        val expectedError = Throwable()

        whenever(mockUserDao.getAll()).thenReturn(Single.error(expectedError))

        sut.getAllUsers().test().assertError(expectedError)

        verify(mockUserDao).getAll()

        verifyNoMoreInteractions(mockUserDao)
    }

    @Test
    fun `test removeAllUsers error`() {
        val expectedError = Throwable()

        whenever(mockUserDao.deleteAll()).thenReturn(Completable.error(expectedError))

        sut.removeAllUsers().test().assertError(expectedError)

        verify(mockUserDao).deleteAll()

        verifyNoMoreInteractions(mockUserDao)
    }
}