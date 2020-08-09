package com.luiscosta.registration.application.di

import com.luiscosta.registration.presentation.confirmation.ConfirmationFragment
import com.luiscosta.registration.presentation.form.FormFragment
import com.luiscosta.registration.presentation.di.ConfirmationModule
import com.luiscosta.registration.presentation.di.FormModule
import com.luiscosta.registration.presentation.di.RegisteredUsersModule
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ContributeModule {

    @ContributesAndroidInjector(modules = [FormModule::class])
    fun bindFormFragment(): FormFragment

    @ContributesAndroidInjector(modules = [ConfirmationModule::class])
    fun bindConfirmationFragment(): ConfirmationFragment

    @ContributesAndroidInjector(modules = [RegisteredUsersModule::class])
    fun bindRegisteredUsersFragment(): RegisteredUsersFragment
}