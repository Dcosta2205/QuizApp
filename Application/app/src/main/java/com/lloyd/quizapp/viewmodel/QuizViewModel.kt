package com.lloyd.quizapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lloyd.quizapp.models.QuestionAnswerModel
import com.lloyd.quizapp.repository.QuizRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class QuizViewModel(val context: Context) : ViewModel() {

    private var mutableLiveData: MutableLiveData<List<QuestionAnswerModel>> = MutableLiveData()
    var liveData: LiveData<List<QuestionAnswerModel>> = mutableLiveData
    private val quizRepository = QuizRepository(context)
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getQuestionsAndAnswers() {
        compositeDisposable.add(
            quizRepository.getQuestionsAndAnswers()
                .map {
                    return@map it
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableLiveData.value = it
                }, {

                })
        )
    }


    fun updateUserScore(score: Int) {
        compositeDisposable.add(
            quizRepository.updateUserScore(score)
                .map {
                    return@map it
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Log", "user data $it")
                }, {

                })
        )
    }

}