package com.luiscosta.registration.presentation.confirmation

import android.util.Log
import com.luiscosta.registration.R
import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ConfirmationPresenter @Inject constructor(
    private val view: ConfirmationContract.View,
    private val userRepository: IUserRepository,
    private val schedulers: BaseSchedulerProvider
) : ConfirmationContract.Presenter {

    private val tag = ConfirmationPresenter::class.java.simpleName

    private val subscriptions = CompositeDisposable()

    override fun getUser(userId: Long) {
        subscriptions.add(
            userRepository
                .getUser(userId)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    view.showName(it.name)
                    view.showEmail(it.email)
                    view.showBirthDate(it.birthDate)
                }, {
                    view.showErrorDialog(R.string.get_user_error)
                    Log.e(tag, it?.message ?: it.toString())
                })
        )
    }

    override fun onViewDestroyed() {
        subscriptions.clear()
    }
}