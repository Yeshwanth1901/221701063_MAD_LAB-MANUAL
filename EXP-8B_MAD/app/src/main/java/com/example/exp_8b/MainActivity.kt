package com.example.exp_8b

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etSubject: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSendEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmail)
        etSubject = findViewById(R.id.etSubject)
        etMessage = findViewById(R.id.etMessage)
        btnSendEmail = findViewById(R.id.btnSendEmail)

        btnSendEmail.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val subject = etSubject.text.toString()
            val message = etMessage.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter a recipient email", Toast.LENGTH_SHORT).show()
            } else {
                sendEmail(email, subject, message)
            }
        }
    }

    private fun sendEmail(email: String, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show()
        }
    }
}
