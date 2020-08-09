package com.luiscosta.registration.presentation.confirmation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luiscosta.registration.R

class ConfirmationActivity : AppCompatActivity() {

    companion object {
        const val USER_ID = "USER_ID"

        fun newIntent(context: Context, userId: Long) =
            Intent(context, ConfirmationActivity::class.java).apply {
                putExtra(USER_ID, userId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
    }
}
