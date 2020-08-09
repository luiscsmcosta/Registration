package com.luiscosta.registration.schedulers.di

import com.luiscosta.registration.schedulers.BaseSchedulerProvider
import com.luiscosta.registration.schedulers.SchedulerProvider
import dagger.Binds
import dagger.Module

@Module(includes = [SchedulersModule.Bindings::class])
class SchedulersModule {

    @Module
    interface Bindings {
        @Binds
        fun bindSchedulers(impl: SchedulerProvider): BaseSchedulerProvider
    }
}