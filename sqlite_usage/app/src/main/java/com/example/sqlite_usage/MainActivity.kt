package com.example.sqlite_usage

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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
        val flushDataButton = findViewById<Button>(R.id.flushDataButton)

        flushRecyclerView()

        // create or upgrade database
        createDBButton.setOnClickListener {
            dbHelper.writableDatabase
        }

        // navigate to add user activity
        addDataButton.setOnClickListener {
            val intent = Intent(this, UserEdit::class.java)
            intent.putExtra("method", "add")
            startActivityForResult(intent, 1)
            Log.i(tag, "add data button clicked")
        }

        flushDataButton.setOnClickListener {
            flushRecyclerView()
        }
    }

    // get all users and append into userList to render recyclerView
    @SuppressLint("Range")
    private fun initUsers() {
        userList.clear()

        val db = dbHelper.writableDatabase
        val cursor = db.query(false, "user", null, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val userName = cursor.getString(cursor.getColumnIndex("user_name"))
                val userAge = cursor.getInt(cursor.getColumnIndex("user_age"))
                val userHobby = cursor.getString(cursor.getColumnIndex("user_hobby"))
                val userCountry = cursor.getString(cursor.getColumnIndex("user_country"))
                Log.i(tag, userName)
                userList.add(User(id, userName, userAge, userHobby, userCountry))
            } while (cursor.moveToNext())
        }
    }

    private fun flushRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(userList)
        initUsers()

        Toast.makeText(this, "flush data successfully", Toast.LENGTH_SHORT).show()
        Log.i(tag, "flsuh data successfully")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
        flushRecyclerView()
    }
}
