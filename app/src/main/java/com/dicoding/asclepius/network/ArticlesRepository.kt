package com.dicoding.asclepius.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepository {
    fun getHealthArticles(
        onSuccess: (List<ArticlesItem>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        ApiClient.articlesApiService.searchHealthArticles("cancer", "health", "en", ApiConfig.API_KEY)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles ?: emptyList()
                        val articlesList = articles.mapNotNull { articles ->
                            if (!articles.title.isNullOrEmpty() && !articles.urlToImage.isNullOrEmpty() && !articles.url.isNullOrEmpty()) {
                                ArticlesItem(articles.title, articles.urlToImage, articles.url)
                            } else {
                                null
                            }
                        }
                        onSuccess(articlesList)
                    } else {
                        onFailure("Failed to fetch articles")
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    onFailure(t.message ?: "Unknown error")
                }
            })
    }
}
