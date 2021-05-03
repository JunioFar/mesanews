package com.example.android.mesanews.api

import com.example.android.mesanews.data.modal.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MesaNewsService {


    @POST("/v1/client/auth/signin")
    suspend fun login(@Body login: LoginRequest) : Response<LoginResponse>

    @POST("/v1/client/auth/signup")
    suspend fun signUp(@Body login: SignUpRequest) : Response<LoginResponse>


    @GET("/v1/client/news/highlights")
    suspend fun getHighlights(@Header("Authorization") token: String) : Response<HighlightsResponse>


    @GET("/v1/client/news")
    suspend fun getNews(
            @Query("current_page") currentPage: Int = 1,
            @Header("Authorization") token: String
    ) : NewsResponse



    companion object {
        private const val BASE_URL = "https://mesa-news-api.herokuapp.com"

        fun create(): MesaNewsService {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MesaNewsService::class.java)
        }
    }
}