package com.luiscosta.registration.presentation.form

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.luiscosta.registration.R
import com.luiscosta.registration.presentation.BaseFragment
import com.luiscosta.registration.presentation.confirmation.ConfirmationActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FormFragment : BaseFragment(), FormContract.View {

    @Inject
    lateinit var presenter: FormContract.Presenter

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var birthDate: EditText

    private lateinit var nameError: TextView
    private lateinit var emailError: TextView
    private lateinit var birthDateError: TextView

    private lateinit var register: Button

    private val textChangedListener = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            if (s.isNotEmpty()) {
                enableRegisterButton(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_form, container, false)

    override fun onResume() {
        super.onResume()

        presenter.clearAllData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.name_et)
        email = view.findViewById(R.id.email_et)
        birthDate = view.findViewById(R.id.birth_date_et)

        name.addTextChangedListener(textChangedListener)
        email.addTextChangedListener(textChangedListener)
        birthDate.addTextChangedListener(textChangedListener)

        nameError = view.findViewById(R.id.name_error_tv)
        emailError = view.findViewById(R.id.email_error_tv)
        birthDateError = view.findViewById(R.id.birth_date_error_tv)

        register = view.findViewById(R.id.register_bt)

        setButtonAction()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.onViewDestroyed()

        name.removeTextChangedListener(textChangedListener)
        email.removeTextChangedListener(textChangedListener)
        birthDate.removeTextChangedListener(textChangedListener)
    }

    override fun showNameError(visible: Boolean) {
        nameError.isVisible(visible)
    }

    override fun showEmailError(visible: Boolean) {
        emailError.isVisible(visible)
    }

    override fun showBirthDateError(visible: Boolean) {
        birthDateError.isVisible(visible)
    }

    override fun enableRegisterButton(enable: Boolean) {
        register.isEnabled = enable
    }

    override fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(
            requireContext(),
            getText(stringRes),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showConfirmationScreen(userId: Long) {
        startActivity(
            ConfirmationActivity.newIntent(
                requireContext(),
                userId
            )
        )
    }

    override fun clearName() {
        name.text.clear()
    }

    override fun clearEmail() {
        email.text.clear()
    }

    override fun clearBirthDate() {
        birthDate.text.clear()
    }

    private fun setButtonAction() {
        register.setOnClickListener {
            presenter.saveUserData(
                name.text.toString(),
                email.text.toString(),
                birthDate.text.toString()
            )
        }
    }

    private fun View.isVisible(visible: Boolean) {
        visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showErrorDialog(stringRes: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(stringRes))
            .setPositiveButton(
                getString(R.string.ok)
            ) { _, _ ->
                clearName()
                clearEmail()
                clearBirthDate()
            }
            .setCancelable(false)
            .create()
            .show()
    }
}