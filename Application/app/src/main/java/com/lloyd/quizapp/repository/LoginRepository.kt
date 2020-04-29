package com.lloyd.quizapp.repository

import android.content.Context
import com.lloyd.quizapp.database.QuizDatabase
import com.lloyd.quizapp.models.User
import io.reactivex.Single

class LoginRepository(val context: Context) {

    fun insertUserIntoDB(userName: String): Single<Long> {
        return QuizDatabase.getDatabase(context)
            .quizDao().insertUsers(User(user_name = userName))
    }
}