package com.example.quizly

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val tv_name = findViewById(R.id.tv_name) as TextView
        tv_name.text = userName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        val tv_score = findViewById(R.id.tv_score) as TextView
        tv_score.text = "Your Score is $correctAnswers out of $totalQuestions."

        val btn_finish = findViewById(R.id.btn_finish) as Button
        btn_finish.setOnClickListener {
            startActivity(Intent(this, LoginIntro::class.java))
        }

    }
}