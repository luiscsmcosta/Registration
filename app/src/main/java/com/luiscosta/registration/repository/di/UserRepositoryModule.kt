package com.luiscosta.registration.repository.di

import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(impl: UserRepository): IUserRepository
}