package com.example.comprehensiveapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.comprehensiveapplication.camera.CameraActivity
import com.example.comprehensiveapplication.guessinggame.GuessingGame
import com.example.comprehensiveapplication.intent.IntentMainActivity
import com.example.comprehensiveapplication.lifecycle.LifeCycleActivity1
import com.example.comprehensiveapplication.multithreadandnetwork.DownloadNetworkImageActivity
import com.example.comprehensiveapplication.sqlite.DBStorageActivity
import com.example.comprehensiveapplication.wxlayout.WXLayoutActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val act1Button = findViewById<Button>(R.id.activity1Button)
        val act2Button = findViewById<Button>(R.id.activity2Button)
        val act3Button = findViewById<Button>(R.id.activity3Button)
        val act4Button = findViewById<Button>(R.id.activity4Button)
        val act5Button = findViewById<Button>(R.id.activity5Button)
        val act6Button = findViewById<Button>(R.id.activity6Button)
        val act7Button = findViewById<Button>(R.id.activity7Button)

        act1Button.setOnClickListener {
            startActivity(Intent(this, LifeCycleActivity1::class.java))
        }
        act2Button.setOnClickListener {
            startActivity(Intent(this, GuessingGame::class.java))
        }
        act3Button.setOnClickListener {
            startActivity(Intent(this, WXLayoutActivity::class.java))
        }
        act4Button.setOnClickListener {
            startActivity(Intent(this, IntentMainActivity::class.java))
        }
        act5Button.setOnClickListener {
            startActivity(Intent(this, DownloadNetworkImageActivity::class.java))
        }
        act6Button.setOnClickListener {
            startActivity(Intent(this, DBStorageActivity::class.java))
        }
        act7Button.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}