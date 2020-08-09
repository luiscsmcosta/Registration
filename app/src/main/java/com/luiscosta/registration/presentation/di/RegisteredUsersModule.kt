package com.luiscosta.registration.presentation.di

import com.luiscosta.registration.presentation.registered_users.RegisteredUsersContract
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersFragment
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersPresenter
import dagger.Binds
import dagger.Module

@Module(includes = [RegisteredUsersModule.Bindings::class])
class RegisteredUsersModule {

    @Module
    interface Bindings {
        @Binds
        fun bindView(impl: RegisteredUsersFragment): RegisteredUsersContract.View

        @Binds
        fun bindPresenter(impl: RegisteredUsersPresenter): RegisteredUsersContract.Presenter
    }
}