package com.luiscosta.registration.presentation.confirmation

import com.luiscosta.registration.presentation.BaseContract

interface ConfirmationContract {

    interface View : BaseContract.View {
        fun showName(name: String)
        fun showEmail(email: String)
        fun showBirthDate(birthDate: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun getUser(userId: Long)
    }

}