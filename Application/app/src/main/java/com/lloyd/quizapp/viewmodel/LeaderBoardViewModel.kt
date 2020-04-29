package com.lloyd.quizapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lloyd.quizapp.models.LeaderBoard
import com.lloyd.quizapp.repository.LeaderBoardRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LeaderBoardViewModel(private val context: Context) : ViewModel() {

    private var mutableLiveData: MutableLiveData<List<LeaderBoard>> = MutableLiveData()
    val liveData: LiveData<List<LeaderBoard>> = mutableLiveData
    private var disposable: Disposable? = null

    fun getAllUsers() {
        disposable = LeaderBoardRepository(context).getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userList ->
                val leaderBoardList = mutableListOf<LeaderBoard>()
                userList.forEach { user ->
                    leaderBoardList.add(LeaderBoard(userName = user.user_name, score = user.score))
                }
                val sortedlist =
                    leaderBoardList.sortedWith(compareByDescending<LeaderBoard> { it.score }.thenBy {
                        it.userName
                    })
                mutableLiveData.value = sortedlist
            }, {

            })
    }
}