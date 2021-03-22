package com.luiscosta.registration.presentation.di

import androidx.fragment.app.Fragment
import com.luiscosta.registration.presentation.di.RegisteredUsersModule.Bindings
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersContract
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersFragment
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module(includes = [Bindings::class])
@InstallIn(FragmentComponent::class)
object RegisteredUsersModule {

    @Provides
    fun bindFragment(fragment: Fragment): RegisteredUsersFragment =
        fragment as RegisteredUsersFragment

    @Module
    @InstallIn(FragmentComponent::class)
    abstract class Bindings {
        @Binds
        abstract fun bindView(impl: RegisteredUsersFragment): RegisteredUsersContract.View

        @Binds
        abstract fun bindPresenter(impl: RegisteredUsersPresenter): RegisteredUsersContract.Presenter
    }
}