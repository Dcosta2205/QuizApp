package com.lloyd.quizapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lloyd.quizapp.interfaces.QuizDao
import com.lloyd.quizapp.models.Answers
import com.lloyd.quizapp.models.Questions
import com.lloyd.quizapp.models.QuizSessions
import com.lloyd.quizapp.models.User

@Database(
    entities = [User::class, Questions::class, Answers::class, QuizSessions::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}