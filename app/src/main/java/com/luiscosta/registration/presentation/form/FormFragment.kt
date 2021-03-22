package com.luiscosta.registration.presentation.form

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.StringRes
import com.luiscosta.registration.R
import com.luiscosta.registration.presentation.BaseFragment
import com.luiscosta.registration.presentation.BaseFragment.MenuType.LIST
import com.luiscosta.registration.presentation.confirmation.ConfirmationActivity
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class FormFragment : BaseFragment(LIST), DatePickerDialog.OnDateSetListener, FormContract.View {

    @Inject
    lateinit var presenter: FormContract.Presenter

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var birthDate: TextView

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


    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
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

        birthDate.setOnClickListener {
            val calendar = Calendar.getInstance()

            val dialog = DatePickerDialog(
                requireContext(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.list_item) {
            startActivity(RegisteredUsersActivity.newIntent(requireContext()))
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        birthDate.setText(SimpleDateFormat(DATE_FORMAT, Locale.ROOT).format(calendar.time))
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
        birthDate.text = ""
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