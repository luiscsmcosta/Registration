package com.luiscosta.registration.application

import android.app.Application
import com.luiscosta.registration.application.di.DaggerTestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerTestApplicationComponent.builder().application(this).context(this).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}