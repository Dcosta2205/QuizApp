package com.lloyd.quizapp.application

import android.app.Application
import com.lloyd.quizapp.database.QuizDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val quizDatabase = QuizDatabase.getDatabase(this)

        val users = quizDatabase.quizDao()
            .getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }
}