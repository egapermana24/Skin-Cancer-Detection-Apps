package com.dicoding.asclepius.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()
    private val _articlesList = MutableLiveData<List<ArticlesItem>>()
    val articlesList: LiveData<List<ArticlesItem>> = _articlesList

    fun fetchHealthArticles() {
        articlesRepository.getHealthArticles(
            onSuccess = { articlesList ->
                _articlesList.postValue(articlesList)
            },
            onFailure = { errorMessage ->
            }
        )
    }
}
