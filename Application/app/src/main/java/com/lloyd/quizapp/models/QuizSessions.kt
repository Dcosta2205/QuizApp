package com.lloyd.quizapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_sessions")
class QuizSessions(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "quiz_session_id") val session_id: Int,
    @ColumnInfo(name = "user_name") val user_name: String,
    @ColumnInfo(name = "score") val score: Int
)