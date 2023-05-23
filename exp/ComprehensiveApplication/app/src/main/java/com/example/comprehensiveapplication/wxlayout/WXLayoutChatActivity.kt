package com.example.comprehensiveapplication.wxlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.comprehensiveapplication.R

class WXLayoutChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wxlayout_chat)

        val backButton = findViewById<Button>(R.id.button0)
        backButton.setOnClickListener {
            startActivity(Intent(this, WXLayoutChatActivity::class.java))
        }
    }
}