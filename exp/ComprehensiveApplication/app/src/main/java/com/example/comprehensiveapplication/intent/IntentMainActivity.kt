package com.example.comprehensiveapplication.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.comprehensiveapplication.R

class IntentMainActivity : AppCompatActivity() {
    private val tag = "FirstActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_main)

        val navButton = findViewById<Button>(R.id.navButton)
        val flushButton = findViewById<Button>(R.id.flushButton)
        val sendSMSButton = findViewById<Button>(R.id.sendSMSButton)
        val textContent = findViewById<TextView>(R.id.textContent)

        // navigate to secondActivity
        navButton.setOnClickListener {
            val data = "Hello I am the msg from first activity."
            val intent = Intent(this, IntentSecondActivity::class.java)
            intent.putExtra("options", data)  // pass parameter
            // this function can get result from second activity after it finish
            startActivityForResult(intent, 1)
        }

        flushButton.setOnClickListener {
            textContent.text = ""
            Toast.makeText(this, "Has cleared", Toast.LENGTH_SHORT).show()
        }
        sendSMSButton.setOnClickListener {
            val uri = Uri.parse("smsto:" + "123456789")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "测试短信")
            startActivity(intent)
        }
    }


    // receive msg from SecondActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedData = data?.getStringExtra("options")
                Log.i(tag, "[returnedData] $returnedData")

                val textContent = findViewById<TextView>(R.id.textContent)
                textContent.text = returnedData
            }
        }
    }
}