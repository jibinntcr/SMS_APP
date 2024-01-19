package com.example.smsapp_dataflair

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var phone: EditText
    private lateinit var messageEdit: EditText
    private lateinit var sendBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        phone = findViewById(R.id.editTextPhone)
        messageEdit = findViewById(R.id.editTextMessage)
        sendBtn = findViewById(R.id.btnSent)

        checkPermissions()

        sendBtn.setOnClickListener {
            sendSMS();
        }
    }

    private fun sendSMS() {
        var phoneNo: String = phone.text.toString()
        var message: String = messageEdit.text.toString()

        if(phoneNo.isNotEmpty() && message.isNotEmpty()){
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, message,null,null)
            Toast.makeText(this,"Text message has been delivered", Toast.LENGTH_SHORT).show()
            phone.text.clear()
            messageEdit.text.clear()
        }
    }

    private fun checkPermissions() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),101)
        }
    }
}