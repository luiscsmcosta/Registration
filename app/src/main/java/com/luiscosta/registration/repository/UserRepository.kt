package com.luiscosta.registration.repository

import com.luiscosta.registration.database.dao.UserDao
import com.luiscosta.registration.database.entity.User
import com.luiscosta.registration.domain.UserDomain
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) : IUserRepository {

    override fun addUser(user: UserDomain): Single<Long> {
        return userDao.insertUser(user.toUserEntity())
    }

    override fun getUser(id: Long): Single<UserDomain> {
        return userDao.getUserById(id).map { user ->
            user.toUserDomain()
        }
    }

    override fun getAllUsers(): Single<List<UserDomain>> {
        return userDao.getAll().map {
            it.map { user ->
                user.toUserDomain()
            }
        }
    }

    private fun UserDomain.toUserEntity(): User {
        return User(
            name = name,
            email = email,
            birthDate = birthDate
        )
    }

    private fun User.toUserDomain(): UserDomain {
        return UserDomain(
            name = name,
            email = email,
            birthDate = birthDate
        )
    }
}