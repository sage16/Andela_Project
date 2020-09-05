package com.example.andelaproject.api

import com.example.andelaproject.model.LeaderIQ
import com.example.andelaproject.model.LearningLeader
import com.example.andelaproject.model.PostDetail
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AndelaApi {

        @GET("/api/skilliq")
        suspend fun getLeaderIQ(
        ): Response<LeaderIQ>

        @GET("/api/hours")
        suspend fun getLearningLeader(
        ): Response<LearningLeader>

        @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
        @FormUrlEncoded
        suspend fun pushPostDetail (
                @Field("entry.1877115667") firstName: String?,
                 @Field("entry.2006916086") lastName: String?,
                 @Field("entry.1824927963") emailAddress: String?,
                 @Field("entry.284483984") githubLink: String?
        ):Response<PostDetail>
}