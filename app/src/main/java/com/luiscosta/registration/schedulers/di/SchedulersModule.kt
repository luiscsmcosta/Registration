package com.luiscosta.registration.schedulers.di

import com.luiscosta.registration.schedulers.BaseSchedulerProvider
import com.luiscosta.registration.schedulers.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
abstract class SchedulersModule {

    @Binds
    abstract fun bindSchedulers(impl: SchedulerProvider): BaseSchedulerProvider
}