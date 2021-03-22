package com.luiscosta.registration.presentation.di

import androidx.fragment.app.Fragment
import com.luiscosta.registration.presentation.confirmation.ConfirmationContract
import com.luiscosta.registration.presentation.confirmation.ConfirmationFragment
import com.luiscosta.registration.presentation.confirmation.ConfirmationPresenter
import com.luiscosta.registration.presentation.di.ConfirmationModule.Bindings
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module(includes = [Bindings::class])
@InstallIn(FragmentComponent::class)
object ConfirmationModule {

    @Provides
    fun bindFragment(fragment: Fragment): ConfirmationFragment = fragment as ConfirmationFragment

    @Module
    @InstallIn(FragmentComponent::class)
    abstract class Bindings {
        @Binds
        abstract fun bindView(impl: ConfirmationFragment): ConfirmationContract.View

        @Binds
        abstract fun bindPresenter(impl: ConfirmationPresenter): ConfirmationContract.Presenter
    }
}