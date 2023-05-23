package com.example.sqlite_usage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.comprehensiveapplication.R
import com.example.comprehensiveapplication.sqlite.UserEditActivity
import org.w3c.dom.Text

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val tag = "UserAdapter"

    init {
        Log.i(tag, "init UserAdapter, userList: $userList")
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userId: TextView = view.findViewById<TextView>(R.id.userId)
        val userName: TextView = view.findViewById<TextView>(R.id.userName)
        val userAge: TextView = view.findViewById<TextView>(R.id.userAge)
        val userHobby: TextView = view.findViewById<TextView>(R.id.userHobby)
        val userCountry: TextView = view.findViewById<TextView>(R.id.userCountry)
        val deleteDataButton: Button = view.findViewById<Button>(R.id.deleteDataButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //to load a view type
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        val viewHolder = ViewHolder(view)

        val dbHelper = MyDatabaseHelper(parent.context, "User.db", 2)

        viewHolder.userName.setOnClickListener {
            editUser(parent.context, viewHolder)
        }
        viewHolder.userAge.setOnClickListener {
            editUser(parent.context, viewHolder)
        }
        viewHolder.userCountry.setOnClickListener {
            editUser(parent.context, viewHolder)
        }
        viewHolder.userHobby.setOnClickListener {
            editUser(parent.context, viewHolder)
        }
        viewHolder.deleteDataButton.setOnClickListener {
            val dialog: AlertDialog = AlertDialog.Builder(parent.context).setTitle("Delete")
                .setMessage("Are you sure you want to delete it?")
                .setPositiveButton(
                    "Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        // delete the selected element
                        val db = dbHelper.writableDatabase
                        db.delete(
                            "user",
                            "user_name = ?",
                            arrayOf(viewHolder.userName.text.toString())
                        )
                        Toast.makeText(
                            parent.context,
                            "has deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }).setNegativeButton("Cancel", null).create()

            dialog.show()

        }
        return viewHolder
    }

    // navigate to UserEdit Activity
    private fun editUser(context: Context, viewHolder: ViewHolder) {
        val intent = Intent(context, UserEditActivity::class.java)
        intent.putExtra("method", "edit")
        intent.putExtra("userId", viewHolder.userId.text.toString())
        intent.putExtra("userName", viewHolder.userName.text.toString())
        intent.putExtra("userAge", viewHolder.userAge.text.toString())
        intent.putExtra("userHobby", viewHolder.userHobby.text.toString())
        intent.putExtra("userCountry", viewHolder.userCountry.text.toString())

        startActivity(context, intent, null)
        Log.i(tag, "editUser button clicked")
    }

    //定义每一个Holder中的内容
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userId.text = user.id.toString()
        holder.userName.text = user.userName
        holder.userAge.text = user.userAge.toString()
        holder.userHobby.text = user.userHobby
        holder.userCountry.text = user.userCountry
    }

    override fun getItemCount() = userList.size
}