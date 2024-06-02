package com.dicoding.asclepius.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val articlesApiService: ArticlesApiService = retrofit.create(ArticlesApiService::class.java)
}
