package com.example.exp_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.*
class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var operand1: Double? = null
    private var pendingOperation: String = ""
    private var isNewInput = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            v.setPadding(

                insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,

                insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,

                insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            )
            insets
        }
        resultTextView = findViewById(R.id.resultTextView)
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9
        )
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onNumberClick(it)
            }
        }
        findViewById<Button>(R.id.buttonDecimal).setOnClickListener {
            if (isNewInput) {
                resultTextView.text = "0."
                isNewInput = false
            } else if (!resultTextView.text.contains(".")) {
                resultTextView.text = resultTextView.text.toString() + "."
            }
        }
        val operationButtons = listOf(
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply,
            R.id.buttonDivide
        )
        operationButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                onOperationClick(it) }
        }
        findViewById<Button>(R.id.buttonEquals).setOnClickListener {
            onEqualsClick() }
        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            onClearClick() }
    }
    private fun onNumberClick(view: View) {
        val button = view as Button
        if (isNewInput) {
            resultTextView.text = button.text
            isNewInput = false
        } else {
            resultTextView.append(button.text)
        }
    }
    private fun onOperationClick(view: View) {
        val button = view as Button
        val operation = button.text.toString()
        val currentNumber = resultTextView.text.toString().toDoubleOrNull()
            ?: return
        if (operand1 == null) {
            operand1 = currentNumber
        } else if (pendingOperation.isNotEmpty()) {
            operand1 = performOperation(operand1!!, currentNumber,
                pendingOperation)
            resultTextView.text = formatResult(operand1!!)
        }
        pendingOperation = operation
        isNewInput = true
    }
    private fun onEqualsClick() {
        if (operand1 != null && pendingOperation.isNotEmpty()) {
            val currentNumber =
                resultTextView.text.toString().toDoubleOrNull() ?: return
            operand1 = performOperation(operand1!!, currentNumber,
                pendingOperation)
            resultTextView.text = formatResult(operand1!!)
            pendingOperation = ""
            operand1 = null
            isNewInput = true
        }
    }
    private fun onClearClick() {
        resultTextView.text = "0"
        operand1 = null
        pendingOperation = ""
        isNewInput = true
    }
    private fun performOperation(operand1: Double, operand2: Double,
                                 operation: String): Double {
        return when (operation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
            else -> operand2
        }
    }
    private fun formatResult(value: Double): String {
        return if (value % 1 == 0.0) value.toInt().toString() else
            value.toString()
    }
}
