package com.luiscosta.registration.presentation.registered_users

import android.util.Log
import com.luiscosta.registration.R
import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.schedulers.BaseSchedulerProvider
import dagger.hilt.android.scopes.FragmentScoped
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

class RegisteredUsersPresenter @Inject constructor(
    private val view: RegisteredUsersContract.View,
    private val userRepository: IUserRepository,
    private val schedulers: BaseSchedulerProvider
) : RegisteredUsersContract.Presenter {

    private val tag = RegisteredUsersPresenter::class.java.simpleName

    private val subscriptions = CompositeDisposable()

    override fun getAllUsers() {
        subscriptions.add(
            userRepository
                .getAllUsers()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                    { list ->
                        view.showUsers(list)
                    },
                    {
                        view.showErrorDialog(R.string.get_all_users_error)
                        Log.e(tag, it?.message ?: it.toString())
                    }
                )
        )
    }

    override fun removeAllUsers() {
        subscriptions.add(
            userRepository
                .removeAllUsers()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                    { list ->
                        view.showUsers(list)
                    },
                    {
                        view.showErrorDialog(R.string.remove_all_users_error)
                        Log.e(tag, it?.message ?: it.toString())
                    }
                )
        )
    }

    override fun onViewDestroyed() {
        subscriptions.clear()
    }
}