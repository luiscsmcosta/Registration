package com.luiscosta.registration.presentation.registered_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}