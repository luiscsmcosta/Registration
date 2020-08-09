package com.luiscosta.registration.presentation

import androidx.annotation.StringRes

interface BaseContract {

    interface View {
        fun showErrorDialog(@StringRes stringRes: Int)
    }

    interface Presenter {
        fun onViewDestroyed()
    }
}