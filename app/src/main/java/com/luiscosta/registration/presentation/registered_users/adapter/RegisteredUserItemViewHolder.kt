package com.luiscosta.registration.presentation.registered_users.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luiscosta.registration.R
import com.luiscosta.registration.domain.UserDomain

class RegisteredUserItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(userDomain: UserDomain) {
        view.findViewById<TextView>(R.id.item_name_tv).text = userDomain.name
        view.findViewById<TextView>(R.id.item_email_tv).text = userDomain.email
        view.findViewById<TextView>(R.id.item_birth_date_tv).text = userDomain.birthDate
    }
}