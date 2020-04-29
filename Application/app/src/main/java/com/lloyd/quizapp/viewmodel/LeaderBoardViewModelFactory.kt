package com.lloyd.quizapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LeaderBoardViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeaderBoardViewModel::class.java)) {
            return LeaderBoardViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}