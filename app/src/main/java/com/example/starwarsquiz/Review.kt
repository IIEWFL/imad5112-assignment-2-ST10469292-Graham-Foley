package com.example.starwarsquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

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

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        val reviewText = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]}\n")
                reviewText.append("Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            reviewTxt.text = reviewText.toString()
        }
        else {
            reviewTxt.text = "Error loading review data."
        }
        restartBtn.setOnClickListener {
            startActivity(quizActivity)
            finish()
        }
        exitBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}