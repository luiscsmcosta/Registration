package com.luiscosta.registration.presentation.confirmation

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.luiscosta.registration.R
import com.luiscosta.registration.presentation.BaseFragment
import com.luiscosta.registration.presentation.BaseFragment.MenuType.LIST
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmationFragment : BaseFragment(LIST), ConfirmationContract.View {

    @Inject
    lateinit var presenter: ConfirmationContract.Presenter

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var birthDate: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_confirmation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.name_tv)
        email = view.findViewById(R.id.email_tv)
        birthDate = view.findViewById(R.id.birth_date_tv)

        requireActivity().intent?.getLongExtra(ConfirmationActivity.USER_ID, -1)?.let {
            presenter.getUser(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.onViewDestroyed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.list_item) {
            startActivity(RegisteredUsersActivity.newIntent(requireContext()))
            requireActivity().finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun showName(name: String) {
        this.name.text = String.format(getString(R.string.confirmation_name), name)
    }

    override fun showEmail(email: String) {
        this.email.text = String.format(getString(R.string.confirmation_email), email)
    }

    override fun showBirthDate(birthDate: String) {
        this.birthDate.text = String.format(getString(R.string.confirmation_birth_date), birthDate)
    }

    override fun showErrorDialog(stringRes: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(stringRes))
            .setPositiveButton(
                getString(R.string.ok)
            ) { _, _ -> requireActivity().finish() }
            .setCancelable(false)
            .create()
            .show()
    }
}