package com.luiscosta.registration.presentation.registered_users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luiscosta.registration.R

class RegisteredUsersActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, RegisteredUsersActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registered_users)
    }
}