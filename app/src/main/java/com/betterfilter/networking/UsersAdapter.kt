package com.betterfilter.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.betterfilter.networking.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(val users: List<User>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.emailTV.text = users[position].email
        holder.itemView.usernameTV.text = users[position].username
        holder.itemView.nameTV.text = users[position].name
        holder.itemView.idTV.text = users[position].id
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val email = v.emailTV
        val username = v.usernameTV
        val name = v.nameTV
        val id = v.id
    }
}