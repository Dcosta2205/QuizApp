package com.lloyd.quizapp.models


import com.google.gson.annotations.SerializedName

data class QuizQuestionsModel(
    @SerializedName("questions")
    val questions: List<OptionsModel>
)