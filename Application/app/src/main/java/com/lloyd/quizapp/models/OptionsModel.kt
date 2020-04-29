package com.lloyd.quizapp.models


import com.google.gson.annotations.SerializedName

data class OptionsModel(
    @SerializedName("options")
    val answers: List<String>,
    @SerializedName("correctIndex")
    val correctIndex: Int,
    @SerializedName("question")
    val question: String,
    @SerializedName("questionId")
    val question_id: Int
)