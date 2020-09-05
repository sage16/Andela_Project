package com.example.andelaproject.repository


import com.example.andelaproject.api.RetrofitInstance
import com.example.andelaproject.api.SubmitRetrofitInstance


class Repository {

    suspend fun getLeaderIQ() =
        RetrofitInstance.getapi.getLeaderIQ()

    suspend fun getLearningLeader() =
        RetrofitInstance.getapi.getLearningLeader()

    suspend fun pushPostDetail(firstName: String, lastName: String, emailAddress: String, githubLink: String) =
        SubmitRetrofitInstance.submitapi.pushPostDetail(firstName, lastName, emailAddress, githubLink)


}