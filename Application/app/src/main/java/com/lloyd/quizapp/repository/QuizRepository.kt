package com.lloyd.quizapp.repository

import android.content.Context
import com.lloyd.quizapp.activities.LoginActivity
import com.lloyd.quizapp.database.QuizDatabase
import com.lloyd.quizapp.models.QuestionAnswerModel
import com.lloyd.quizapp.models.User
import io.reactivex.Single

class QuizRepository(private val context: Context) {

    fun getQuestionsAndAnswers(): Single<List<QuestionAnswerModel>> {
        return QuizDatabase.getDatabase(context).quizDao().getQuestionAnswers()
    }

    fun updateUserScore(score: Int): Single<Int> {
        return QuizDatabase.getDatabase(context).quizDao().updateUserScore(score, LoginActivity.userName)
    }
}