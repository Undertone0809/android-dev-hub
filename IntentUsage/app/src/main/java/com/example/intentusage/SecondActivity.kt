package com.example.intentusage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {
    private val tag = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

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