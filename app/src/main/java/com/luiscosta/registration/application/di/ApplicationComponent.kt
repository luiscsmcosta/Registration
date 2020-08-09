package com.luiscosta.registration.application.di

import android.content.Context
import com.luiscosta.registration.application.RegistrationApplication
import com.luiscosta.registration.database.di.DatabaseModule
import com.luiscosta.registration.repository.di.UserRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        DatabaseModule::class,
        UserRepositoryModule::class,
        ContributeModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<RegistrationApplication> {
    override fun inject(application: RegistrationApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: RegistrationApplication): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}