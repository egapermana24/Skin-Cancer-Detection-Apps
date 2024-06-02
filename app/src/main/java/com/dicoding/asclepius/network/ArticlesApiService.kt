package com.dicoding.asclepius.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiService {
    @GET("top-headlines")
    fun searchHealthArticles(
        @Query("q") query: String,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): Call<ArticlesResponse>
}
