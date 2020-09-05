package com.example.andelaproject.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andelaproject.R
import com.example.andelaproject.adapter.LeaderIQAdapter
import com.example.andelaproject.adapter.LearningLeaderAdapter
import com.example.andelaproject.repository.Repository
import com.example.andelaproject.util.Resource
import kotlinx.android.synthetic.main.leader_iq.*
import kotlinx.android.synthetic.main.learning_leaders.*

class LeaderIQFragment: Fragment(R.layout.leader_iq) {

    lateinit var  viewModel: MainViewModel
    lateinit var leaderIQAdapter: LeaderIQAdapter
    val TAG = "LeaderIQActivity"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel = (activity as MainActivity).viewModel

        val viewModelProviderFactory = MainViewModelProviderFactory(
            Repository()
        )
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(
            MainViewModel::class.java
        )
        viewModel.leaderIQ.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { LeaderIQResponse ->
                        leaderIQAdapter.differ.submitList(LeaderIQResponse)

                    }
                }
                is Resource.Error -> {
                    response.data?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        leaderIQAdapter = LeaderIQAdapter()
        rvLeaderIQ.apply {
            adapter = leaderIQAdapter
            layoutManager = LinearLayoutManager(activity)
        }


    }

    }








































































































