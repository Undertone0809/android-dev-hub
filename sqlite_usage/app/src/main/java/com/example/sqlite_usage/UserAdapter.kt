package com.example.sqlite_usage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val userList: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById<TextView>(R.id.userName)
        val userAge: TextView = view.findViewById<TextView>(R.id.userAge)
        val userHobby: TextView = view.findViewById<TextView>(R.id.userHobby)
        val userCountry: TextView = view.findViewById<TextView>(R.id.userCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //to load a view type
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        val viewHolder = ViewHolder(view)

        viewHolder.userName.setOnClickListener {
            editUser()
        }
        viewHolder.userAge.setOnClickListener {
            editUser()
        }
        viewHolder.userCountry.setOnClickListener {
            editUser()
        }
        viewHolder.userHobby.setOnClickListener {
            editUser()
        }
        return viewHolder
    }

    private fun editUser() {

    }

    //定义每一个Holder中的内容
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.userName
        holder.userAge.text = user.userAge.toString()
        holder.userHobby.text = user.userHobby
        holder.userCountry.text = user.userCountry
    }

    override fun getItemCount() = userList.size
}