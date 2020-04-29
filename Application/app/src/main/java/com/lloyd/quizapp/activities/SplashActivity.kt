package com.lloyd.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.lloyd.quizapp.R
import com.lloyd.quizapp.viewmodel.SplashViewModel
import com.lloyd.quizapp.viewmodel.SplashViewModelFactory
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    private var hasAnimationStarted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val viewModel =
            SplashViewModelFactory(this).create(SplashViewModel::class.java)
        viewModel.getAllQuestionsFromDB()
        viewModel.liveData.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                viewModel.insertQuestionsIntoDB()

            }
        })
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun animateText() {
        hasAnimationStarted = true
        tv_welcome_to.animate()
            .translationX((root.width - tv_welcome_to.width) / 2.toFloat())
            .translationY((root.height - tv_welcome_to.height) / 2.toFloat())
            .setInterpolator(LinearInterpolator()).duration = 500

        tv_masai_school.animate()
            .translationX((tv_masai_school.width - root.width) / 2.toFloat())
            .translationY((tv_masai_school.height - root.height) / 2.5.toFloat())
            .setInterpolator(LinearInterpolator()).duration = 500
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasAnimationStarted.not()) {
            animateText()
        }
    }
}
