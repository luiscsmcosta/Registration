package com.luiscosta.registration.application.di

import android.content.Context
import com.luiscosta.registration.application.TestApplication
import com.luiscosta.registration.database.di.DatabaseModule
import com.luiscosta.registration.repository.di.TestUserRepositoryModule
import com.luiscosta.registration.schedulers.di.SchedulersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        SchedulersModule::class,
        DatabaseModule::class,
        TestUserRepositoryModule::class,
        ContributeModule::class
    ]
)
interface TestApplicationComponent : AndroidInjector<TestApplication> {
    override fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: TestApplication): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): TestApplicationComponent
    }
}