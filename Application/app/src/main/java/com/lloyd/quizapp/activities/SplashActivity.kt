package com.lloyd.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.lloyd.quizapp.R
import com.lloyd.quizapp.viewmodel.SplashViewModel
import com.lloyd.quizapp.viewmodel.SplashViewModelFactory

class SplashActivity : AppCompatActivity() {

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
}
