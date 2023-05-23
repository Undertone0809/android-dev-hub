package com.example.sqlite_usage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(private val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createUser = "create table user (" +
            "id integer primary key autoincrement," +
            "user_name text," +
            "user_age integer," +
            "user_hobby text," +
            "user_country text)"

    private val createTodo = "create table todo (" +
            "todo_id integer primary key autoincrement," +
            "todo_name text," +
            "todo_pub_time datetime," +
            "todo_status integer," +
            "todo_modified_time datetime)"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(createUser)
        db.execSQL(createTodo)
        Toast.makeText(context, "create successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists user")
        db.execSQL("drop table if exists todo")
        onCreate(db)
    }
}