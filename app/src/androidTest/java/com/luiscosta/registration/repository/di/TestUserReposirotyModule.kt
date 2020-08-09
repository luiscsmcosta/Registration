package com.luiscosta.registration.repository.di

import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.repository.FakeUserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [TestUserRepositoryModule.Bindings::class])
class TestUserRepositoryModule {

    @Module
    interface Bindings {

        @Binds
        fun bindUserRepository(impl: FakeUserRepository): IUserRepository

    }
}