package com.luiscosta.registration.presentation.registered_users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain
import javax.inject.Inject

class RegisteredUsersAdapter @Inject constructor() :
    RecyclerView.Adapter<RegisteredUserItemViewHolder>() {

    private val items = mutableListOf<UserDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RegisteredUserItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_registered_user, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RegisteredUserItemViewHolder, position: Int) =
        holder.bind(items[position])

    fun bind(list: List<UserDomain>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}