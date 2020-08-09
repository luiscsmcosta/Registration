package com.luiscosta.registration.repository

import com.luiscosta.registration.domain.UserDomain
import io.reactivex.Single

interface IUserRepository {

    fun addUser(user: UserDomain): Single<Long>

    fun getUser(id: Long): Single<UserDomain>

    fun getAllUsers(): Single<List<UserDomain>>

    fun removeAllUsers(): Single<List<UserDomain>>

}