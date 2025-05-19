package com.example.starwarsquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

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
        questionTxt = findViewById(R.id.questionTxt)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        resultTxt = findViewById(R.id.resultTxt)
        nextBtn = findViewById(R.id.nextBtn)
        exitBtn = findViewById(R.id.exitBtn)

        displayQuestion()

        //Disable "Next" button
        nextBtn.isEnabled = false
        //True check button
        trueBtn.setOnClickListener { checkAnswer(true) }
        //False check button
        falseBtn.setOnClickListener { checkAnswer(false) }

        //Next button
        nextBtn.setOnClickListener {
            nextQuestion()
        }
        //Exit program
        exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
    private var questionIndex = 0
    private var score = 0

    private lateinit var questionTxt: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var resultTxt: TextView
    private lateinit var nextBtn: Button
    private lateinit var exitBtn: Button

    companion object {
        //Question list
        val questions = arrayOf(
            "Darth Vader is Luke Skywalker's father",
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
        //Answer list
        val answers = booleanArrayOf(true, true, false, true, false, false, true, true, false, true)
    }
    //Display questions
    private fun displayQuestion() {
        questionTxt.text = questions[questionIndex]
    }
    //True or false checker
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[questionIndex]

        //True check
        if (userAnswer == correctAnswer) {
            resultTxt.text = "Correct"
            resultTxt.setTextColor(Color.GREEN)
            //Increase score
            score++
        }
        //False check
        else {
            resultTxt.text = "Incorrect"
            resultTxt.setTextColor(Color.RED)
        }
        //Disable "True" and "False" button
        trueBtn.isEnabled = false
        falseBtn.isEnabled = false
        //Enable "Next" button
        nextBtn.isEnabled = true
    }
    //"Next" button function
    private fun nextQuestion() {
        //Increment question list
        questionIndex++
        if (questionIndex < questions.size) {
            displayQuestion()
            resultTxt.text = ""
            //Enable "True" and "False" button
            trueBtn.isEnabled = true
            falseBtn.isEnabled = true
            //Disable "Next" button
            nextBtn.isEnabled = false
        } else {
            val scoreActivity = Intent(this, Score::class.java)
            //Put score into scoreActivity
            scoreActivity.putExtra("score", score)
            //Switch to scoreActivity
            startActivity(scoreActivity)
            finish()
        }
    }
}