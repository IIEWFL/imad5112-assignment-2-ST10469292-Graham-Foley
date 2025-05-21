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
        val resultsTxt = findViewById<TextView>(R.id.resultsTxt)
        val reviewBtn = findViewById<Button>(R.id.reviewBtn)
        val restartBtn = findViewById<Button>(R.id.restartBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

        val quizActivity = Intent(this, Quiz::class.java)
        val reviewActivity = Intent(this, Review::class.java)

        //Get score
        val score = intent.getIntExtra("score", 0)
        scoreTxt.text = "Score: $score/10"

        val results = if (score >= 9) {
            "Excellent!"
        }
        else {
            if (score >= 5) {
                "Good Job!"
            } else {
                if (score == 0) {
                    "Better Luck Next Time"
                } else {
                    "Keep Trying!"
                }
            }
        }
        //Display feedback
        resultsTxt.text = results

        //Switch to Review
        reviewBtn.setOnClickListener {
            //Get questions from Quiz and put into Review
            reviewActivity.putExtra("questions", intent.getStringArrayExtra("questions"))
            //Get answers from Quiz and put into Review
            reviewActivity.putExtra("answers", intent.getBooleanArrayExtra("answers"))
            startActivity(reviewActivity)
            finish()
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