package com.luiscosta.registration.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.luiscosta.registration.R
import com.luiscosta.registration.presentation.BaseFragment.MenuType.CLEAR
import com.luiscosta.registration.presentation.BaseFragment.MenuType.LIST

abstract class BaseFragment(private val menuType: MenuType) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        when (menuType) {
            LIST -> menu.findItem(R.id.list_item).isVisible = true
            CLEAR -> menu.findItem(R.id.clear_item).isVisible = true
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    enum class MenuType {
        LIST,
        CLEAR
    }
}