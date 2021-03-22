package com.luiscosta.registration.presentation.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luiscosta.registration.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
    }
}