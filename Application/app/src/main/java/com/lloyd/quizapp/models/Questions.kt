package com.lloyd.quizapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_table")
data class Questions(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "answerIndex") val answerIndex: Int
)