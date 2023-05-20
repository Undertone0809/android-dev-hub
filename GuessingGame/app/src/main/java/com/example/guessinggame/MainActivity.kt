package com.example.guessinggame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var tag = "MainActivity"
    private var confirmBtn: Button? = null
    private var radioGroup1: RadioGroup? = null
    private var radioGroup2: RadioGroup? = null
    private var selectedRadioButton1: RadioButton? = null
    private var selectedRadioButton2: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        confirmBtn = findViewById<Button>(R.id.confirmButton)
        radioGroup1 = findViewById<RadioGroup>(R.id.radioGroup1)
        radioGroup2 = findViewById<RadioGroup>(R.id.radioGroup2)

        confirmBtn!!.setOnClickListener {
            Log.i(tag, "click confirm")
            val result =
                getGussingGameResult(selectedRadioButton1!!.text, selectedRadioButton2!!.text)
            Log.i(tag, "result is $result")
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }

        radioGroup1!!.setOnCheckedChangeListener { group, checkedId ->
            selectedRadioButton1 = group.findViewById(checkedId) as RadioButton
        }

        radioGroup2!!.setOnCheckedChangeListener { group, checkedId ->
            selectedRadioButton2 = group.findViewById(checkedId) as RadioButton
        }
    }

    fun getGussingGameResult(a: CharSequence, b: CharSequence): String {
        // dogfall
        if (a == b) {
            return "dogfall!"
        }
        // win
        if ((a == "Rock" && b == "Scissors") || (a == "Scissors" && b == "Paper") || (a == "Paper" && b == "Rock")) {
            return "you win!"
        }
        // failed
        return "you failed!"
    }
}