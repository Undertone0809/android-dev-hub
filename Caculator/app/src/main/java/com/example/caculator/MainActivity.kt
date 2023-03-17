package com.example.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var lastNumber: String = ""
    private var currentNumber: String = ""
    private var operation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button0.setOnClickListener { numberPressed("0") }
        button1.setOnClickListener { numberPressed("1") }
        button2.setOnClickListener { numberPressed("2") }
        button3.setOnClickListener { numberPressed("3") }
        button4.setOnClickListener { numberPressed("4") }
        button5.setOnClickListener { numberPressed("5") }
        button6.setOnClickListener { numberPressed("6") }
        button7.setOnClickListener { numberPressed("7") }
        button8.setOnClickListener { numberPressed("8") }
        button9.setOnClickListener { numberPressed("9") }
        buttonDecimal.setOnClickListener { numberPressed(".") }

        buttonClear.setOnClickListener { clearPressed() }
        buttonPlusMinus.setOnClickListener { plusMinusPressed() }
        buttonPercent.setOnClickListener { percentPressed() }

        buttonDivide.setOnClickListener { operationPressed("/") }
        buttonMultiply.setOnClickListener { operationPressed("*") }
        buttonMinus.setOnClickListener { operationPressed("-") }
        buttonPlus.setOnClickListener { operationPressed("+") }

        buttonEquals.setOnClickListener { equalPressed() }
    }

    private fun numberPressed(number: String) {
        if (operation.isEmpty()) {
            lastNumber += number
            resultTextView.text = lastNumber
        } else {
            currentNumber += number
            resultTextView.text = currentNumber
        }
    }

    private fun clearPressed() {
        lastNumber = ""
        currentNumber = ""
        operation = ""
        resultTextView.text = "0"
    }

    private fun plusMinusPressed() {
        if (operation.isEmpty()) {
            lastNumber = (-1 * lastNumber.toDouble()).toString()
            resultTextView.text = lastNumber
        } else {
            currentNumber = (-1 * currentNumber.toDouble()).toString()
            resultTextView.text = currentNumber
        }
    }

    private fun percentPressed() {
        if (operation.isEmpty()) {
            lastNumber = (lastNumber.toDouble() / 100).toString()
            resultTextView.text = lastNumber
        } else {
            currentNumber = (currentNumber.toDouble() / 100).toString()
            resultTextView.text = currentNumber
        }
    }

    private fun operationPressed(operator: String) {
        if (currentNumber.isNotEmpty()) {
            equalPressed()
        }
        operation = operator
    }

    private fun equalPressed() {
        if (currentNumber.isNotEmpty()) {
            val last = lastNumber.toDouble()
            val current = currentNumber.toDouble()

            when (operation) {
                "+" -> lastNumber = (last + current).toString()
                "-" -> lastNumber = (last - current).toString()
                "*" -> lastNumber = (last * current).toString()
                "/" -> lastNumber = (last / current).toString()
            }

            currentNumber = ""
            operation = ""
            resultTextView.text = lastNumber
        }
    }
}
