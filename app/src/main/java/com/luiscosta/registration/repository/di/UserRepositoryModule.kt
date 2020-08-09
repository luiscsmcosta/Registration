package com.luiscosta.registration.repository.di

import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [UserRepositoryModule.Bindings::class])
class UserRepositoryModule {

    @Module
    interface Bindings {

        @Binds
        fun bindUserRepository(impl: UserRepository): IUserRepository

    }
}