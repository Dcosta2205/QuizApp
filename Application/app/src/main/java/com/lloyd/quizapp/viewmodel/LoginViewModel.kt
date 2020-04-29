package com.lloyd.quizapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lloyd.quizapp.repository.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val context: Context) : ViewModel() {

    private val loginRepository = LoginRepository(context)

    fun insertUsersIntoDB(userName: String) {
        loginRepository.insertUserIntoDB(userName).map {

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}