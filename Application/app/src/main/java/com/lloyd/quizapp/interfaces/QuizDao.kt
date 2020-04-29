package com.lloyd.quizapp.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.lloyd.quizapp.models.Answers
import com.lloyd.quizapp.models.QuestionAnswerModel
import com.lloyd.quizapp.models.Questions
import com.lloyd.quizapp.models.User
import io.reactivex.Single

@Dao
interface QuizDao {

    @Insert
    fun insertUsers(user: User): Single<Long>

    @Query("SELECT * FROM  users_table")
    fun getAllUsers(): Single<List<User>>

    @Query("UPDATE users_table SET user_score =:score WHERE user_name= :userName")
    fun updateUserScore(score: Int, userName: String) :Single<Int>

    @Insert
    fun insertQuestion(questions: Questions): Single<Long>

    @Query("SELECT * FROM questions_table")
    fun getAllQuestions(): Single<List<Questions>>

    @Insert
    fun insertOptions(answers: Answers): Single<Long>

    @Query("SELECT * FROM answers_table")
    fun getOptions(): Single<List<Answers>>

    @Transaction
    @Query("SELECT q.answerIndex, q.question, q.id, a.question_id, a.id,a.options  FROM questions_table q, answers_table a where q.id = a.id")
    fun getQuestionAnswers(): Single<List<QuestionAnswerModel>>
}