package com.luiscosta.registration.application

import android.app.Application
import com.luiscosta.registration.application.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RegistrationApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().application(this).context(this).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}