package com.lloyd.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.lloyd.quizapp.R
import com.lloyd.quizapp.interfaces.DialogListener
import com.lloyd.quizapp.models.QuestionAnswerModel
import com.lloyd.quizapp.utils.CustomDialog
import com.lloyd.quizapp.viewmodel.QuizViewModel
import com.lloyd.quizapp.viewmodel.QuizViewModelFactory
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.options_text_layout.view.*


class QuizActivity : AppCompatActivity(), DialogListener {

    private lateinit var viewModel: QuizViewModel
    private var questionsAttempted = 1
    private var score = 0
    private var index = 0
    private var timer = 0
    private var isRight: Boolean = false
    private var isTimeElapsed = false
    private lateinit var questionAnswerModelList: List<QuestionAnswerModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        viewModel = QuizViewModelFactory(this).create(QuizViewModel::class.java)
        initViewsAndListeners()
        viewModel.liveData.observe(this, Observer {
            Log.d("Lloyd", "Live data ")
            questionAnswerModelList = it
            populateOptionsLayout()
        })
    }

    private fun initViewsAndListeners() {
        val customDialog = CustomDialog(this, this)
        customDialog.setCancelable(false)
        customDialog.show()
        tv_next_question.setOnClickListener {
            countDownTimer.cancel()
            if (isTimeElapsed.not()) {
                timer = 0
            }
            if (index < questionAnswerModelList.size - 1) {
                index++
                questionsAttempted++
                if (isRight) {
                    score++
                }
                populateOptionsLayout()
            } else {
                countDownTimer.cancel()
                if (isRight) {
                    score++
                }
                viewModel.updateUserScore(score)
                showScoreLayout()
            }
            tv_score_count.text = score.toString()
        }

        btn_viewLeaderBoard.setOnClickListener {
            val intent = Intent(this@QuizActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showScoreLayout() {
        ll_question_answer.visibility = View.GONE
        layout_score.visibility = View.VISIBLE
        tv_score.text = score.toString()
        tv_score.animation =
            AnimationUtils.loadAnimation(this@QuizActivity, R.anim.zoom_in_animation                                                                                                            )

    }

    private fun populateOptionsLayout() {
        ll_dynamic_options.removeAllViews()
        countDownTimer.start()
        if (questionAnswerModelList.isNotEmpty()) {
            questionAnswerModelList.apply {
                tv_question_attempts.text =
                    String.format(
                        getString(R.string.question_text),
                        questionsAttempted,
                        this.size
                    )

                tv_question.text = this[index].questions.question

                val view = LayoutInflater.from(this@QuizActivity)
                    .inflate(R.layout.options_text_layout, null, false)
                this[index].answers.forEachIndexed { questionIndex, answers ->

                    val optionsTextView = RadioButton(view.context)
                    optionsTextView.text = answers.option
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    optionsTextView.background =
                        ContextCompat.getDrawable(view.context, R.drawable.options_layout_bg)
                    isRight = false
                    optionsTextView.setOnClickListener {
                        isRight = this[index].questions.answerIndex == questionIndex
                    }
                    params.setMargins(20, 0, 0, 40)
                    optionsTextView.setPadding(30, 40, 8, 40)
                    optionsTextView.layoutParams = params
                    optionsTextView.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.color_white
                        )
                    )
                    view.radiogroup.addView(optionsTextView)
                }
                ll_dynamic_options.addView(view)

            }

        }
    }

    private val countDownTimer = object : CountDownTimer(30000, 1000) {
        override fun onFinish() {
            timer = 0
            isTimeElapsed = true
            cancel()
            if (index <= questionAnswerModelList.size - 1) {
                tv_next_question.performClick()
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            timer++
            progressbar.progress = (millisUntilFinished / 1000).toInt()
            isTimeElapsed = false
        }

    }

    override fun onDismiss() {
        viewModel.getQuestionsAndAnswers()
    }
}
