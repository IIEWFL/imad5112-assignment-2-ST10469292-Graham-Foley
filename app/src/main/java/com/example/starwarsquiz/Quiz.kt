package com.example.starwarsquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private lateinit var questionTxt: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var resultTxt: TextView
    private lateinit var nextBtn: Button
    private lateinit var exitBtn: Button

    companion object {
        val questions = arrayOf(
            "Luke Skywalker's father Darth Vader",
            "Darth Vader is Anakin Skywalker",
            "Wookiees are from Endor",
            "General Grievous uses 4 lightsabers",
            "General Grievous was Force-sensitive",
            "Count Dooku was always a Sith",
            "Cal Kestis can sense Force Echoes",
            "Han Solo was an orphan",
            "Kylo Ren is Luke Skywalker's son",
            "Rey Skywalker is Emperor Palpatine's granddaughter"
        )
    }
}