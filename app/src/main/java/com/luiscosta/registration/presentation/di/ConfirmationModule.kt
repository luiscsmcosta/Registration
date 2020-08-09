package com.luiscosta.registration.presentation.di

import com.luiscosta.registration.presentation.confirmation.ConfirmationContract
import com.luiscosta.registration.presentation.confirmation.ConfirmationFragment
import com.luiscosta.registration.presentation.confirmation.ConfirmationPresenter
import dagger.Binds
import dagger.Module

@Module(includes = [ConfirmationModule.Bindings::class])
class ConfirmationModule {

    @Module
    interface Bindings {
        @Binds
        fun bindView(impl: ConfirmationFragment): ConfirmationContract.View

        @Binds
        fun bindPresenter(impl: ConfirmationPresenter): ConfirmationContract.Presenter
    }
}