package com.luiscosta.registration.presentation.registered_users

import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.presentation.BaseContract

interface RegisteredUsersContract {

    interface View : BaseContract.View {
        fun showUsers(list: List<UserDomain>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getAllUsers()
        fun removeAllUsers()
    }
}