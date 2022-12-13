package com.example.quizly

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class LoginIntro() : AppCompatActivity() {

    lateinit var btnGetStarted: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        supportActionBar?.hide()

        btnGetStarted = findViewById(R.id.btnGetStarted)
        btnGetStarted.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}