package com.lloyd.quizapp.models

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionAnswerModel(
    @Embedded val questions: Questions,
    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val answers: List<Answers>
)