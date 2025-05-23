package com.example.starwarsquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val reviewTxt = findViewById<TextView>(R.id.reviewTxt)
        val restartBtn = findViewById<Button>(R.id.restartBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

        val quizActivity = Intent(this, Quiz::class.java)

        //Get questions from Score
        val questions = intent.getStringArrayExtra("questions")
        //Get answers from Score
        val answers = intent.getBooleanArrayExtra("answers")

        val reviewText = StringBuilder()
        //Determine if questions array size equals answers array size
        if (questions != null && answers != null && questions.size == answers.size) {
            //Set questions with answers in reviewText
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]}\n")
                reviewText.append("Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            //Display questions with answers
            reviewTxt.text = reviewText.toString()
        }
        else {
            //Display error if error occurs
            reviewTxt.text = "Error loading review data."
        }
        //Switch to Quiz
        restartBtn.setOnClickListener {
            startActivity(quizActivity)
            finish()
        }
        //Exit program
        exitBtn.setOnClickListener {
            finishAffinity()
        }
    }
}