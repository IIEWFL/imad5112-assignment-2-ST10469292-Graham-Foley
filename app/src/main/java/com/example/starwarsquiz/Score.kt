package com.example.starwarsquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val scoreTxt = findViewById<TextView>(R.id.scoreTxt)
        val feedbackTxt = findViewById<TextView>(R.id.feedbackTxt)
        val reviewBtn = findViewById<Button>(R.id.reviewBtn)
        val restartBtn = findViewById<Button>(R.id.restartBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)
        val quizActivity = Intent(this, Quiz::class.java)

        val score = intent.getIntExtra("score",0)
        scoreTxt.text = "Score: $score/10"

        val feedback = if (score >= 5) {
            "Good Job!"
        }
        else {
            if (score >= 9) {
                "Excellent!"
            } else {
                if (score == 0) {
                    "Better Luck Next Time"
                } else {
                    "Keep Trying!"
                }
            }
        }
        feedbackTxt.text = feedback

        restartBtn.setOnClickListener {
                startActivity(quizActivity)
                finish()
        }
    }
}