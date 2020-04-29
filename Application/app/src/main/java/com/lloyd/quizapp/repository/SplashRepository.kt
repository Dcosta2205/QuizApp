package com.lloyd.quizapp.repository

import android.content.Context
import android.util.Log
import com.lloyd.quizapp.database.QuizDatabase
import com.lloyd.quizapp.mapper.DataMapper
import com.lloyd.quizapp.models.Answers
import com.lloyd.quizapp.models.OptionsModel
import com.lloyd.quizapp.models.Questions
import com.lloyd.quizapp.models.QuizQuestionsModel
import com.lloyd.quizapp.utils.QUESTIONS_JSON_FILE_NAME
import io.reactivex.Observable
import io.reactivex.Single

class SplashRepository(private val context: Context) {
    private val quizDatabase = QuizDatabase.getDatabase(context)
    private val dataMapper = DataMapper()
    private var optionModelList: List<OptionsModel>? = null
    private lateinit var quizQuestionsModel: QuizQuestionsModel


    fun insertQuestionsIntoDB(): Observable<Long> {
        return Single.just(fetchDataFromLocalJSON())
            .map {
                quizQuestionsModel = dataMapper.buildQuizQuestionModel(it)
                return@map quizQuestionsModel.questions
            }
            .toObservable()
            .flatMapIterable {
                it
            }
            .flatMap { question ->
                val questionsModel =
                    Questions(question.question_id, question.question, question.correctIndex)
                return@flatMap quizDatabase.quizDao().insertQuestion(questionsModel)
                    .toObservable()
                    .flatMapIterable {
                        question.answers
                    }
                    .flatMap {
                        val answerModel = Answers(option = it, question_id = questionsModel.id)
                        return@flatMap quizDatabase.quizDao().insertOptions(answerModel)
                            .toObservable()
                    }
            }
    }

    private fun fetchDataFromLocalJSON(): String {
        var json = ""
        try {
            json =
                context.assets.open(QUESTIONS_JSON_FILE_NAME).bufferedReader().use { it.readText() }
            Log.d("Lloyd ", "Json fetched $json")

        } catch (e: Exception) {
            Log.d("Lloyd", "Exception here is " + e.message)
        }
        return json
    }


    fun getAllQuestionsFromDB(): Single<List<Questions>> {
        return QuizDatabase.getDatabase(context).quizDao()
            .getAllQuestions()
    }
}