package com.example.quizly

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView


class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    private var mUserName: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizqs)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        val btn_submit = findViewById(R.id.btn_submit) as Button
        val tv_option_one = findViewById(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById(R.id.tv_option_four) as TextView
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }



    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tv_option_one -> {
                val tv_option_one = findViewById(R.id.tv_option_one) as TextView

                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {
                val tv_option_two = findViewById(R.id.tv_option_two) as TextView

                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {
                val tv_option_three = findViewById(R.id.tv_option_three) as TextView

                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {
                val tv_option_four = findViewById(R.id.tv_option_four) as TextView

                selectedOptionView(tv_option_four, 4)
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                                val intent = Intent(this, ResultActivity::class.java)
                                intent.putExtra(Constants.USER_NAME, mUserName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                                startActivity(intent)

                            }
                        }

                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        val btn_submit = findViewById(R.id.btn_submit) as Button
                        btn_submit.text = "FINISH"


                    } else {
                        val btn_submit = findViewById(R.id.btn_submit) as Button
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun setQuestion() {

        val question = mQuestionsList!!.get(mCurrentPosition - 1)

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            val btn_submit = findViewById(R.id.btn_submit) as Button
            btn_submit.text = "FINISH"
        } else {
            val btn_submit = findViewById(R.id.btn_submit) as Button
            btn_submit.text = "SUBMIT"
        }

        val progressBar = findViewById(R.id.progressBar) as ProgressBar
        progressBar.progress = mCurrentPosition
        val tv_progress= findViewById(R.id.tv_progress) as TextView
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        val tv_question = findViewById(R.id.tv_question) as TextView
        tv_question.text = question.question
        val iv_image = findViewById(R.id.iv_image) as ImageView
        iv_image.setImageResource(question.image)
        val tv_option_one = findViewById(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById(R.id.tv_option_four) as TextView
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        val tv_option_one = findViewById(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById(R.id.tv_option_four) as TextView
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                val tv_option_one = findViewById(R.id.tv_option_one) as TextView

                tv_option_one.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                val tv_option_two = findViewById(R.id.tv_option_two) as TextView

                tv_option_two.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                val tv_option_three = findViewById(R.id.tv_option_three) as TextView

                tv_option_three.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                val tv_option_four = findViewById(R.id.tv_option_four) as TextView

                tv_option_four.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}