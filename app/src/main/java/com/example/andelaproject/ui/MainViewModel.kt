package com.example.andelaproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andelaproject.model.LeaderIQ
import com.example.andelaproject.model.LearningLeader
import com.example.andelaproject.model.PostDetail
import com.example.andelaproject.repository.Repository
import com.example.andelaproject.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (val repository: Repository): ViewModel() {

    val leaderIQ: MutableLiveData<Resource<LeaderIQ>> = MutableLiveData()

    val learningLeader: MutableLiveData<Resource<LearningLeader>> = MutableLiveData()

    val postDetail: MutableLiveData<Resource<PostDetail>> = MutableLiveData()

    init {
        getLeaderIQ()
        getLearningLeader()

    }

    private fun  getLeaderIQ() = viewModelScope.launch {
        leaderIQ.postValue(Resource.Loading())
        val response = repository.getLeaderIQ()
        leaderIQ.postValue(handleleaderIQ(response))
    }

    fun handleleaderIQ(response: Response<LeaderIQ>) : Resource<LeaderIQ>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun getLearningLeader()  = viewModelScope.launch {
        learningLeader.postValue(Resource.Loading())
        val response = repository.getLearningLeader()
        learningLeader.postValue(handlelearningLeader(response))
    }

    fun handlelearningLeader(response: Response<LearningLeader>) : Resource<LearningLeader>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

     fun  getPostDetail(firstName: String, lastName: String, emailAddress: String, githubLink: String)
            = viewModelScope.launch {
        postDetail.postValue(Resource.Loading())
        val response = repository.pushPostDetail(firstName, lastName, emailAddress, githubLink)
        postDetail.postValue(handlepostDetail(response))
    }

    fun handlepostDetail(response: Response<PostDetail>) : Resource<PostDetail>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}