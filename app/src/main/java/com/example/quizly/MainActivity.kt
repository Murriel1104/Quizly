package com.example.quizly

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btn_start: Button
    lateinit var etTextTextPersonName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        btn_start = findViewById(R.id.btn_start)
        etTextTextPersonName = findViewById(R.id.etTextTextPersonName)

        btn_start.setOnClickListener{
            val intent= Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, etTextTextPersonName.text.toString())
            startActivity(intent)
            finish()

        }
    }
}
