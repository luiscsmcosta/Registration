package com.luiscosta.registration.presentation.form

import android.util.Log
import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.repository.IUserRepository
import com.luiscosta.registration.schedulers.BaseSchedulerProvider
import dagger.hilt.android.scopes.FragmentScoped
import io.reactivex.disposables.CompositeDisposable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FormPresenter @Inject constructor(
    private val view: FormContract.View,
    private val userRepository: IUserRepository,
    private val schedulers: BaseSchedulerProvider
) : FormContract.Presenter {

    private val tag = FormPresenter::class.java.simpleName

    private val subscriptions = CompositeDisposable()

    override fun saveUserData(name: String, email: String, birthDate: String) {
        if (name.isEmpty() || email.isEmpty() || birthDate.isEmpty()) {
            view.showToast(R.string.fill_all_fields)
        } else if (isNameValid(name) && isEmailValid(email) && isBirthDateValid(birthDate)) {
            val user = UserDomain(name, email, birthDate)

            subscriptions.add(
                userRepository
                    .addUser(user)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe(
                        {
                            view.showConfirmationScreen(it)
                        },
                        {
                            view.showErrorDialog(R.string.add_user_error)
                            Log.e(tag, it?.message ?: it.toString())
                        }
                    )
            )
        }

        view.enableRegisterButton(false)

        validateName(name)
        validateEmail(email)
        validateBirthDate(birthDate)
    }

    override fun clearAllData() {
        view.clearName()
        view.clearEmail()
        view.clearBirthDate()
        view.enableRegisterButton(true)
    }

    override fun onViewDestroyed() {
        subscriptions.clear()
    }

    private fun validateName(name: String) {
        if (isNameValid(name)) {
            view.showNameError(false)
        } else {
            view.showNameError(true)
        }
    }

    private fun validateEmail(email: String) {
        if (isEmailValid(email)) {
            view.showEmailError(false)
        } else {
            view.showEmailError(true)
        }
    }

    private fun validateBirthDate(date: String) {
        if (isBirthDateValid(date)) {
            view.showBirthDateError(false)
        } else {
            view.showBirthDateError(true)
        }
    }

    private fun isNameValid(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return email.matches(Regex("([a-zA-Z0-9](\\.[a-zA-Z0-9])*)+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]{2,})+"))
    }

    private fun isBirthDateValid(birthDate: String): Boolean {
        val start: Date = Calendar.getInstance().apply {
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.YEAR, 1900)
            set(Calendar.DAY_OF_MONTH, 1)
        }.time
        val end: Date = Calendar.getInstance().time

        return try {
            val date: Date? = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).parse(birthDate)

            date?.let { it ->
                it in start..end
            } ?: false
        } catch (exception: ParseException) {
            false
        }
    }
}