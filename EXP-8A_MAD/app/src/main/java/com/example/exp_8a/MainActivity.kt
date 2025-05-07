package com.example.exp_8a

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etPhone: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    private val SMS_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPhone = findViewById(R.id.etPhoneNumber)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.SEND_SMS),
                    SMS_PERMISSION_CODE
                )
            } else {
                sendSms()
            }
        }
    }

    private fun sendSms() {
        val phoneNumber = etPhone.text.toString()
        val message = etMessage.text.toString()

        if (phoneNumber.isBlank() || message.isBlank()) {
            Toast.makeText(this, "Enter both phone number and message", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(this, "SMS sent successfully!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send SMS: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == SMS_PERMISSION_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSms()
        } else {
            Toast.makeText(this, "SMS permission denied.", Toast.LENGTH_SHORT).show()
        }
    }
}
