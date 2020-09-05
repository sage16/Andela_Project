package com.example.andelaproject.api

import com.example.andelaproject.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SubmitRetrofitInstance {
    companion object {
        private val submitRetrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_POST)
                .build()
        }

        val submitapi by lazy {
         submitRetrofit.create(AndelaApi::class.java)
        }
    }
}