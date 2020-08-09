package com.luiscosta.registration.presentation.form

import androidx.annotation.StringRes
import com.luiscosta.registration.presentation.BaseContract

interface FormContract {

    interface View : BaseContract.View {
        fun showNameError(visible: Boolean)
        fun showEmailError(visible: Boolean)
        fun showBirthDateError(visible: Boolean)
        fun enableRegisterButton(enable: Boolean)
        fun showToast(@StringRes stringRes: Int)
        fun showConfirmationScreen(userId: Long)

        fun clearName()
        fun clearEmail()
        fun clearBirthDate()
    }

    interface Presenter : BaseContract.Presenter {
        fun saveUserData(name: String, email: String, birthDate: String)
        fun clearAllData()
    }
}