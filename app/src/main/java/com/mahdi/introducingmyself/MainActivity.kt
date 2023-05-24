package com.mahdi.introducingmyself

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val mHandler = Handler()
    private var stopToastCounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToastRunnable.run()
        initializeTextViewName()
        initialTextViewsIntroduction()
        initializeButtons()
    }

    @SuppressLint("DiscouragedApi")
    private fun initialTextViewsIntroduction() {
        val textViewH1 = findViewById<TextView>(R.id.textViewF1)
        val textViewT1 = findViewById<TextView>(R.id.textViewF1T)
        val textViewH2 = findViewById<TextView>(R.id.textViewS2)
        val textViewT2 = findViewById<TextView>(R.id.textViewS2T)
        val textViewH3 = findViewById<TextView>(R.id.textViewT3)
        val textViewT3 = findViewById<TextView>(R.id.textViewT3T)
        val textViewH4 = findViewById<TextView>(R.id.textViewF4)
        val textViewT4 = findViewById<TextView>(R.id.textViewF4T)
        val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "introduction_p", "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
        val outputJsonString = JSONObject(jsonData)
        Log.d("Tag Data", "" + outputJsonString)

        val parts = outputJsonString.getJSONArray("parts") as JSONArray
        var title = parts.getJSONObject(0).get("title")
        var text = parts.getJSONObject(0).get("text")
        textViewH1.text = title.toString()
        textViewT1.text = text.toString()
        title = parts.getJSONObject(1).get("title")
        text = parts.getJSONObject(1).get("text")
        textViewH2.text = title.toString()
        textViewT2.text = text.toString()
        title = parts.getJSONObject(2).get("title")
        text = parts.getJSONObject(2).get("text")
        textViewH3.text = title.toString()
        textViewT3.text = text.toString()
        title = parts.getJSONObject(3).get("title")
        text = parts.getJSONObject(3).get("text")
        textViewH4.text = title.toString()
        textViewT4.text = text.toString()
    }


    private val mToastRunnable = object : Runnable {
        override fun run() {
            if (stopToastCounter < 5) {
                Toast.makeText(this@MainActivity, "Welcome", Toast.LENGTH_SHORT).show()
                print(stopToastCounter)
                stopToastCounter += 1
                mHandler.postDelayed(this, 0)
            }
        }
    }

    private fun initializeButtons() {
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        button1.setOnClickListener {
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
        val myName = "Mahid Davoodzadehd"
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