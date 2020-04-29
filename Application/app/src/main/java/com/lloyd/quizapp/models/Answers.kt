package com.lloyd.quizapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "answers_table",
    foreignKeys = [ForeignKey(
        entity = Questions::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("question_id"),
        onDelete = CASCADE
    )]
)
data class Answers(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "options") val option: String,
    @ColumnInfo(name = "question_id") val question_id: Int
)