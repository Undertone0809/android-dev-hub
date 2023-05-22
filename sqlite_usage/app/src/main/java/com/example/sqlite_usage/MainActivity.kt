package com.example.sqlite_usage

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val dbHelper = MyDatabaseHelper(this, "User.db", 2)
    private val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createDBButton = findViewById<Button>(R.id.createDBButton)
        val addDataButton = findViewById<Button>(R.id.addDataButton)
        val updateDataButton = findViewById<Button>(R.id.updateDataButton)
        val deleteDataButton = findViewById<Button>(R.id.deleteDataButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        getAllUsers()

        // render recycler
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter

        // create or upgrade database
        createDBButton.setOnClickListener {
            dbHelper.writableDatabase
        }

        // navigate to add user activity
        addDataButton.setOnClickListener {
            val intent = Intent(this, UserEdit::class.java)
            startActivityForResult(intent, 1)
        }

        updateDataButton.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("user", values, "name = ?", arrayOf("Jack"))
            Toast.makeText(this, "Update data successfully", Toast.LENGTH_SHORT).show()
        }
    }

    // get all users and append into userList to render recyclerView
    private fun getAllUsers() {
        val db = dbHelper.writableDatabase
        val cursor = db.query(false, "user", null, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val userName = cursor.getString(cursor.getColumnIndex("user_name"))
                val userAge = cursor.getInt(cursor.getColumnIndex("user_age"))
                val userHobby = cursor.getString(cursor.getColumnIndex("user_hobby"))
                val userCountry = cursor.getString(cursor.getColumnIndex("user_country"))
                Log.i(tag, userName)
                userList.add(User(userName, userAge, userHobby, userCountry))
            } while (cursor.moveToNext())
        }
    }
}
