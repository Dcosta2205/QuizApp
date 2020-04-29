package com.lloyd.quizapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val user_id: Int = 0,
    @ColumnInfo(name = "user_name") val user_name: String,
    @ColumnInfo(name = "user_score") val score: Int = 0
)