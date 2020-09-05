package com.example.andelaproject.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andelaproject.R
import com.example.andelaproject.adapter.LearningLeaderAdapter
import com.example.andelaproject.repository.Repository
import com.example.andelaproject.util.Resource
import kotlinx.android.synthetic.main.learning_leaders.*

class LearningLeadersFragment: Fragment(R.layout.learning_leaders) {

    lateinit var viewModel: MainViewModel
    lateinit var learningLeaderAdapter: LearningLeaderAdapter
    val TAG = "LearningLeaderActivity"

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
        viewModel.learningLeader.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { learningLeaderResponse ->
                        learningLeaderAdapter.differ.submitList(learningLeaderResponse)

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
        learningLeaderAdapter = LearningLeaderAdapter()
        rvLearningLeader.apply {
            adapter = learningLeaderAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}



