package com.example.andelaproject.api
import com.example.andelaproject.util.Constants.Companion.BASE_URL_GET
import com.example.andelaproject.util.Constants.Companion.BASE_URL_POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_GET)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        val getapi by lazy {
            retrofit.create(AndelaApi::class.java)
        }
    }
}

