package com.example.exp_1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var s = 0
        var c = 0
        val textSizes = arrayOf(25f, 30f, 35f)
        val colors = arrayOf(Color.RED, Color.BLUE, Color.GREEN)
        var isGray = true
        val tv1: TextView = findViewById(R.id.tv1)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val linearLayout: LinearLayout = findViewById(R.id.main)
        linearLayout.setBackgroundColor(Color.GRAY)

        btn1.setOnClickListener {
            tv1.textSize = textSizes[s]
            s = (s + 1) % textSizes.size
        }
        btn2.setOnClickListener {
            tv1.setTextColor(colors[c])
            c = (c + 1) % colors.size
        }
        btn3.setOnClickListener {
            // Toggle background color
            if (isGray) {
                linearLayout.setBackgroundColor(Color.WHITE)
            } else {
                linearLayout.setBackgroundColor(Color.GRAY)
            }
            isGray = !isGray // Switch the state
        }
    }
}
