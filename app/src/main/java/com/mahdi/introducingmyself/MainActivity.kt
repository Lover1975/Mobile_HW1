package com.mahdi.introducingmyself

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeTextViewName()
        initializeButtons()
    }

    private fun initializeButtons() {
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        button1.setOnClickListener{
            val intentPhoneNumber = Intent(Intent.ACTION_DIAL)
            intentPhoneNumber.data = Uri.parse("tel:09302639518")
            startActivity(intentPhoneNumber)
        }
        button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("MahdiDavoodzadeh81@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
            intent.putExtra(Intent.EXTRA_TEXT, "mail body")
            startActivity(Intent.createChooser(intent, ""))
        }
    }

    private fun initializeTextViewName() {
        val textViewName = findViewById<TextView>(R.id.textView1)
        val myName = "Mahid Davoodzadehdsfgdfsgdfgsdfg"
        var myNameForTextView = ""
        var counter = 0
        for (element in myName) {
            if (counter <= 20) {
                myNameForTextView += element
            } else {
                myNameForTextView += "..."
                break
            }
            counter += 1
        }
        textViewName.text = myNameForTextView
    }
}