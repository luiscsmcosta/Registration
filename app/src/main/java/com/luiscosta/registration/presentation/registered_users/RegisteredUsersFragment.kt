package com.luiscosta.registration.presentation.registered_users

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import com.luiscosta.registration.presentation.registered_users.adapter.RegisteredUsersAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegisteredUsersFragment : Fragment(), RegisteredUsersContract.View {

    @Inject
    lateinit var presenter: RegisteredUsersContract.Presenter

    @Inject
    lateinit var adapter: RegisteredUsersAdapter

    private lateinit var usersList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.clear_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  if(item.itemId == R.id.clear_item) {
            presenter.removeAllUsers()

            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_registered_users, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersList = view.findViewById(R.id.users_list_rv)

        usersList.adapter = adapter

        presenter.getAllUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.onViewDestroyed()
    }

    override fun showUsers(list: List<UserDomain>) {
        adapter.bind(list)
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