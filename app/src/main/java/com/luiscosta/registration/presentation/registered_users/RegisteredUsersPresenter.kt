package com.luiscosta.registration.presentation.registered_users

import android.util.Log
import com.luiscosta.registration.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisteredUsersPresenter @Inject constructor(
    private val view: RegisteredUsersContract.View,
    private val userRepository: IUserRepository
) : RegisteredUsersContract.Presenter {

    private val tag = RegisteredUsersPresenter::class.java.simpleName

    private val subscriptions = CompositeDisposable()

    override fun getAllUsers() {
        subscriptions.add(
            userRepository
                .getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { list ->
                        view.showUsers(list)
                    },
                    {
                        Log.e(tag, it?.message ?: it.toString())
                    }
                )
        )
    }

    override fun onViewDestroyed() {
        subscriptions.clear()
    }
}