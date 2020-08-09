package com.luiscosta.registration.presentation.di

import com.luiscosta.registration.presentation.form.FormContract
import com.luiscosta.registration.presentation.form.FormFragment
import com.luiscosta.registration.presentation.form.FormPresenter
import dagger.Binds
import dagger.Module

@Module(includes = [FormModule.Bindings::class])
class FormModule {

    @Module
    interface Bindings {
        @Binds
        fun bindView(impl: FormFragment): FormContract.View

        @Binds
        fun bindPresenter(impl: FormPresenter): FormContract.Presenter
    }
}