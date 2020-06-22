package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textview = findViewById<TextView>(R.id.textview)
        var time = LocalTime.parse("2:11 PM", DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
        println("這裡啊>>>" + time)
    }
}

