package com.lloyd.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.lloyd.quizapp.R
import com.lloyd.quizapp.models.LeaderBoard
import com.lloyd.quizapp.viewmodel.LeaderBoardViewModel
import com.lloyd.quizapp.viewmodel.LeaderBoardViewModelFactory
import kotlinx.android.synthetic.main.activity_leader_board.*

class LeaderBoardActivity : AppCompatActivity() {

    private lateinit var viewModel: LeaderBoardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)
        viewModel = LeaderBoardViewModelFactory(this).create(LeaderBoardViewModel::class.java)
        viewModel.getAllUsers()
        initViewsAndListeners()
        viewModel.liveData.observe(this, Observer {
            fl_progressBar.visibility = View.GONE
            rl_leaderboard.visibility = View.VISIBLE
            inflateDynamicView(it)
        })
    }

    private fun initViewsAndListeners() {
        tv_playAgain.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun inflateDynamicView(leaderBoardList: List<LeaderBoard>) {
        leaderBoardList.forEachIndexed { index, leaderBoard ->
            val view = LayoutInflater.from(this@LeaderBoardActivity)
                .inflate(R.layout.dynamic_leaderboard, null, false)
            val rankTextView: TextView = view.findViewById(R.id.tv_leaderboard_rank)
            val userNameTextView: TextView = view.findViewById(R.id.tv_leaderboard_username)
            val scoreView: TextView = view.findViewById(R.id.tv_score)
            rankTextView.text = (index + 1).toString()
            userNameTextView.text = leaderBoard.userName
            scoreView.text = leaderBoard.score.toString()
            ll_dynamic_leaderboard.addView(view)
        }
    }

}
