package com.example.sqlite_usage

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class UserEdit : AppCompatActivity() {
    private val dbHelper = MyDatabaseHelper(this, "User.db", 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit)

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val saveButton = findViewById<Button>(R.id.saveDataButton)
        val userName = findViewById<TextView>(R.id.userName)
        val userAge = findViewById<TextView>(R.id.userAge)
        val userHobby = findViewById<TextView>(R.id.userHobby)
        val userCountry = findViewById<TextView>(R.id.userCountry)

        // save data to database
        saveButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("user_name", userName.text.toString())
                put("user_age", userAge.text.toString())
                put("user_hobby", userHobby.text.toString())
                put("user_country", userCountry.text.toString())
            }
            db.insert("user", null, value)
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}