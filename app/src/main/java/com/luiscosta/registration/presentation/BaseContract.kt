package com.luiscosta.registration.presentation

interface BaseContract {

    interface View

    interface Presenter {
        fun onViewDestroyed()
    }
}