package com.luiscosta.registration

import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.luiscosta.registration.application.TestApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ) = super.newApplication(cl, TestApplication::class.java.name, context)
}