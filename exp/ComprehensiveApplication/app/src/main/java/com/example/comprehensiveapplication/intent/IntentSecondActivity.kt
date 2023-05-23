package com.example.comprehensiveapplication.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.comprehensiveapplication.R


class IntentSecondActivity : AppCompatActivity() {
    private val tag = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_second)

        val navButton = findViewById<Button>(R.id.navButton)
        val textContent = findViewById<TextView>(R.id.textContent)

        // show data
        val extraData = intent.getStringExtra("options")
        Log.i(tag, extraData!!)
        textContent.text = extraData

        // navigate back to firstActivity
        navButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("options", "Hello I am the msg from second activity.")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}