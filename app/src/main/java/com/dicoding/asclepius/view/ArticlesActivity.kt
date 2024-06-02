package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.adapter.ArticlesListAdapter
import com.dicoding.asclepius.databinding.ActivityArticlesBinding
import com.dicoding.asclepius.network.ArticlesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ArticlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticlesBinding
    private lateinit var articlesListAdapter: ArticlesListAdapter
    private lateinit var articlesViewModel: ArticlesViewModel
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationView = findViewById(R.id.menuBar)
        newsRecyclerView = findViewById(R.id.rvArticlesList)
        bottomNavigationView.selectedItemId = R.id.article
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.article -> {
                    true
                }
                R.id.history_menu -> {
                    startActivity(Intent(this, HistoryActivity::class.java))
                    true
                }
                else -> false
            }
        }
        initRecyclerView()

        articlesViewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        articlesViewModel.fetchHealthArticles()
        articlesViewModel.articlesList.observe(this, Observer { articlesList ->
            articlesListAdapter.submitList(articlesList)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openArticle(view: View) {
        val url = view.getTag(R.id.tvLink) as? String
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        articlesListAdapter = ArticlesListAdapter()
        binding.rvArticlesList.apply {
            adapter = articlesListAdapter
            layoutManager = LinearLayoutManager(this@ArticlesActivity)
        }
    }
}
