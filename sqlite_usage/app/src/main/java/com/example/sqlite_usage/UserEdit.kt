package com.example.sqlite_usage

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class UserEdit : AppCompatActivity() {
    private val tag = "UserEdit Activity"
    private val dbHelper = MyDatabaseHelper(this, "User.db", 2)
    private var method: String = "add"

    private var cancelButton: Button? = null
    private var saveButton: Button? = null
    private var userId: TextView? = null
    private var userName: TextView? = null
    private var userAge: TextView? = null
    private var userHobby: TextView? = null
    private var userCountry: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit)

        cancelButton = findViewById<Button>(R.id.cancelButton)
        saveButton = findViewById<Button>(R.id.saveDataButton)
        userId = findViewById<TextView>(R.id.userId)
        userName = findViewById<TextView>(R.id.userName)
        userAge = findViewById<TextView>(R.id.userAge)
        userHobby = findViewById<TextView>(R.id.userHobby)
        userCountry = findViewById<TextView>(R.id.userCountry)

        userId?.visibility = View.INVISIBLE
        handleIntent()

        // save data to database
        saveButton!!.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("user_name", userName?.text.toString())
                put("user_age", userAge?.text.toString().toInt())
                put("user_hobby", userHobby?.text.toString())
                put("user_country", userCountry?.text.toString())
            }
            if (method == "add") {
                db.insert("user", null, value)
                Log.i(tag, "save user successfully")
                Toast.makeText(this, "save user successfully", Toast.LENGTH_SHORT).show()
            } else if (method == "edit") {
                Log.i(tag, "userId 11111111111"+userId?.text.toString())
                db.update("user", value, "id = ?", arrayOf(userId?.text.toString()))
                Log.i(tag, "edit user successfully")
                Toast.makeText(this, "edit user successfully", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        cancelButton!!.setOnClickListener {
            Log.i(tag, "cancel button clicked")
            finish()
        }
    }

    private fun handleIntent() {
        method = intent.getStringExtra("method").toString()
        Log.i(tag, "current method is: $method")
        if (method == "edit") {
            userId?.text = intent.getStringExtra("userId")
            userName?.text = intent.getStringExtra("userName")
            userAge?.text = intent.getStringExtra("userAge")
            userHobby?.text = intent.getStringExtra("userHobby")
            userCountry?.text = intent.getStringExtra("userCountry")
        }
    }
}