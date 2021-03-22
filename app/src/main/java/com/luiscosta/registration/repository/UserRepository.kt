package com.luiscosta.registration.repository

import com.luiscosta.registration.database.dao.UserDao
import com.luiscosta.registration.database.entity.User
import com.luiscosta.registration.domain.UserDomain
import dagger.hilt.android.scopes.FragmentScoped
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository @Inject constructor(private val userDao: UserDao) : IUserRepository {

    override fun addUser(user: UserDomain) = userDao.insertUser(user.toUserEntity())

    override fun getUser(id: Long) = userDao.getUserById(id).map { user ->
        user.toUserDomain()
    }

    override fun getAllUsers() = userDao.getAll().map {
        it.map { user ->
            user.toUserDomain()
        }
    }

    override fun removeAllUsers(): Single<List<UserDomain>> = userDao.deleteAll().toSingle {
        emptyList<UserDomain>()
    }

    private fun UserDomain.toUserEntity() = User(
        name = name,
        email = email,
        birthDate = birthDate
    )

    private fun User.toUserDomain() = UserDomain(
        name = name,
        email = email,
        birthDate = birthDate
    )
}