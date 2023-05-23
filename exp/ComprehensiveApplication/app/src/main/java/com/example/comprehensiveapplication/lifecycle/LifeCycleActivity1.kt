package com.example.comprehensiveapplication.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.comprehensiveapplication.R

class LifeCycleActivity1 : AppCompatActivity() {

    private val tag = "LifeCycleActivity1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_life_cycle1)

        val cancelButton = findViewById<Button>(R.id.cancel)
        val startSecondActivityButton = findViewById<Button>(R.id.startSecondActivity)

        // navigate to second activity
        startSecondActivityButton.setOnClickListener {
            val intent = Intent(this, LifeCycleActivity2::class.java)
            Log.i(tag, "click cancel button")
            startActivity(intent)
        }

        // exit program
        cancelButton.setOnClickListener {
            Log.i(tag, "click exit button")
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }
}