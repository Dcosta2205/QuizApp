package com.lloyd.quizapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lloyd.quizapp.models.Questions
import com.lloyd.quizapp.repository.SplashRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashViewModel(context: Context) : ViewModel() {
    private val splashRepository = SplashRepository(context)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mutableLiveData: MutableLiveData<List<Questions>> = MutableLiveData()
    val liveData: LiveData<List<Questions>> = mutableLiveData

    fun insertQuestionsIntoDB() {
        compositeDisposable.add(
            splashRepository.insertQuestionsIntoDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Lloyd", "Value is $it")
                }, {

                })
        )

    }

    fun getAllQuestionsFromDB() {
        compositeDisposable.add(
            splashRepository.getAllQuestionsFromDB()
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

}