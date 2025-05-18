package com.example.starwarsquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val startBtn = findViewById<Button>(R.id.startBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)
        val quizActivity = Intent(this, Quiz::class.java)

        startBtn.setOnClickListener {
            startActivity(quizActivity)
            finish()
        }
        exitBtn.setOnClickListener {
            exitProcess(0)
        }
    }
}