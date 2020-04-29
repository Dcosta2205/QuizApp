package com.lloyd.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amitshekhar.DebugDB
import com.lloyd.quizapp.R
import com.lloyd.quizapp.viewmodel.LoginViewModel
import com.lloyd.quizapp.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    companion object {
         var userName: String = ""
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewsAndListeners()
        Log.d("LLoyd", " amit shekhar " + DebugDB.getAddressLog())
        viewModel = LoginViewModelFactory(this).create(LoginViewModel::class.java)

    }

    private fun initViewsAndListeners() {
        et_enter_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                userName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        tv_start_quiz.setOnClickListener {
            if (userName.isNullOrEmpty()) {
                et_enter_name.error = "Please Enter a username"
            } else if (userName.length < 3) {
                et_enter_name.error = "Please enter 3 letters as username"
            } else {
                viewModel.insertUsersIntoDB(userName)
                val intent = Intent(this@LoginActivity, QuizActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
