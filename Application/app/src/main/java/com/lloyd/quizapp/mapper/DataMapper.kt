package com.lloyd.quizapp.mapper

import com.google.gson.Gson
import com.lloyd.quizapp.models.OptionsModel
import com.lloyd.quizapp.models.Questions
import com.lloyd.quizapp.models.QuizQuestionsModel

class DataMapper {

    private var quizQuestionsModel: QuizQuestionsModel? = null


    private fun getListOfQuestionsFromJSON(localJSON: String): QuizQuestionsModel {

        val gson = Gson()
        return gson.fromJson(localJSON, QuizQuestionsModel::class.java)
    }




    fun buildQuizQuestionModel(jsonData: String): QuizQuestionsModel {
        return getListOfQuestionsFromJSON(jsonData)
    }
}