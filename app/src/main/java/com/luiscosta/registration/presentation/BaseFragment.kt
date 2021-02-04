package com.luiscosta.registration.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.luiscosta.registration.R
import com.luiscosta.registration.presentation.registered_users.RegisteredUsersActivity

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.list_item) {
            startActivity(RegisteredUsersActivity.newIntent(requireContext()))

            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}