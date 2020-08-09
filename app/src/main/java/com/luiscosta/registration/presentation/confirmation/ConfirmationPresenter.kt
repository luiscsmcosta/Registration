package com.luiscosta.registration.presentation.confirmation

import android.util.Log
import com.luiscosta.registration.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ConfirmationPresenter @Inject constructor(
    private val view: ConfirmationContract.View,
    private val userRepository: IUserRepository
) : ConfirmationContract.Presenter {

    private val tag = ConfirmationPresenter::class.java.simpleName

    private val subscriptions = CompositeDisposable()

    override fun getUser(userId: Long) {
        subscriptions.add(
            userRepository
                .getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showName(it.name)
                    view.showEmail(it.email)
                    view.showBirthDate(it.birthDate)
                }, {
                    Log.e(tag, it?.message ?: it.toString())
                })
        )
    }

    override fun onViewDestroyed() {
        subscriptions.clear()
    }
}