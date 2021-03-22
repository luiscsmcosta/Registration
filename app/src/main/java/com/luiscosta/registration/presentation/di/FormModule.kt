package com.luiscosta.registration.presentation.di

import androidx.fragment.app.Fragment
import com.luiscosta.registration.presentation.di.FormModule.Bindings
import com.luiscosta.registration.presentation.form.FormContract
import com.luiscosta.registration.presentation.form.FormFragment
import com.luiscosta.registration.presentation.form.FormPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module(includes = [Bindings::class])
@InstallIn(FragmentComponent::class)
object FormModule {

    @Provides
    fun bindFragment(fragment: Fragment): FormFragment = fragment as FormFragment

    @Module
    @InstallIn(FragmentComponent::class)
    abstract class Bindings {
        @Binds
        abstract fun bindView(impl: FormFragment): FormContract.View

        @Binds
        abstract fun bindPresenter(impl: FormPresenter): FormContract.Presenter
    }
}