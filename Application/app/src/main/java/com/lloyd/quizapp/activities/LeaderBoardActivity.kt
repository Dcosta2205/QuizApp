package com.lloyd.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            inflateDynamicView(it)
        })
    }

    private fun initViewsAndListeners() {
        btn_play_again.setOnClickListener {
            val intent = Intent(this@LeaderBoardActivity, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun inflateDynamicView(leaderBoardList: List<LeaderBoard>) {
        val rankRow = TableRow(this@LeaderBoardActivity)
        val rankRowTextView = TextView(this@LeaderBoardActivity)
        val params = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

        createDynamicRankRows(rankRowTextView, params, rankRow, getString(R.string.rank_text))
        createDynamicUserNameRows(params, rankRowTextView, rankRow, getString(R.string.username))
        createDynamicScoreRows(params, rankRowTextView, rankRow, getString(R.string.score_text))

        leaderBoardList.forEachIndexed { index, leaderBoard ->
            val rankRow = TableRow(this@LeaderBoardActivity)
            val rankRowTextView = TextView(this@LeaderBoardActivity)
            val params = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

            createDynamicRankRows(rankRowTextView, params, rankRow, (index+1).toString())
            createDynamicUserNameRows(params, rankRowTextView, rankRow, leaderBoard.userName.toUpperCase())
            createDynamicScoreRows(params, rankRowTextView, rankRow,  leaderBoard.score.toString())

        }
    }

    private fun createDynamicScoreRows(
        params: TableRow.LayoutParams,
        rankRowTextView: TextView,
        rankRow: TableRow, text: String
    ) {
        val scoreTextView = TextView(this@LeaderBoardActivity)
        scoreTextView.layoutParams = params
        scoreTextView.setPadding(20, 50, 60, 20)
        scoreTextView.textSize = 18.0f
        scoreTextView.gravity = Gravity.CENTER
        scoreTextView.text = text
        scoreTextView.setTextColor(
            ContextCompat.getColor(
                rankRowTextView.context,
                R.color.color_white
            )
        )
        rankRow.addView(scoreTextView)
        table_main.addView(rankRow)
    }

    private fun createDynamicUserNameRows(
        params: TableRow.LayoutParams,
        rankRowTextView: TextView,
        rankRow: TableRow, text: String
    ) {
        val userNameTextView = TextView(this@LeaderBoardActivity)
        userNameTextView.layoutParams = params
        userNameTextView.setPadding(20, 50, 60, 20)
        userNameTextView.textSize = 18.0f
        userNameTextView.gravity = Gravity.CENTER
        userNameTextView.text = text
        userNameTextView.setTextColor(
            ContextCompat.getColor(
                rankRowTextView.context,
                R.color.color_white
            )
        )
        rankRow.addView(userNameTextView)
    }

    private fun createDynamicRankRows(
        rankRowTextView: TextView,
        params: TableRow.LayoutParams,
        rankRow: TableRow, text: String
    ) {
        rankRowTextView.text = text
        rankRowTextView.layoutParams = params
        rankRowTextView.setPadding(20, 50, 60, 20)
        rankRowTextView.gravity = Gravity.CENTER
        rankRowTextView.setTextColor(
            ContextCompat.getColor(
                rankRowTextView.context,
                R.color.color_white
            )
        )
        rankRowTextView.textSize = 18.0f
        rankRow.addView(rankRowTextView)
    }

}
