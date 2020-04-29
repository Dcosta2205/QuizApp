package com.lloyd.quizapp.repository

import android.content.Context
import com.lloyd.quizapp.database.QuizDatabase
import com.lloyd.quizapp.models.User
import io.reactivex.Single

class LeaderBoardRepository(val context: Context) {

    fun getAllUsers(): Single<List<User>> {
        return QuizDatabase.getDatabase(context).quizDao().getAllUsers()
    }
}