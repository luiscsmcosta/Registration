package com.luiscosta.registration.repository

import com.luiscosta.registration.domain.UserDomain
import io.reactivex.Single
import javax.inject.Inject

class FakeUserRepository @Inject constructor(): IUserRepository {

    companion object {
        var fakeUser = UserDomain("Luis", "luis@ab.ab", "1984-01-01")
    }

    override fun addUser(user: UserDomain): Single<Long> {
        return Single.just(0)
    }

    override fun getUser(id: Long): Single<UserDomain> {
        return Single.just(fakeUser)
    }

    override fun getAllUsers(): Single<List<UserDomain>> {
        return Single.just(listOf(fakeUser))
    }

    override fun removeAllUsers(): Single<List<UserDomain>> {
        return Single.just(emptyList<UserDomain>())
    }

}