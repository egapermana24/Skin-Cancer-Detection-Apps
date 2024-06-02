package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.network.ArticlesItem

class ArticlesListAdapter : ListAdapter<ArticlesItem, ArticlesListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_articles, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val imgNews: ImageView = itemView.findViewById(R.id.imgNews)

        fun bind(articlesItem: ArticlesItem) {
            tvTitle.text = articlesItem.title
            itemView.findViewById<TextView>(R.id.tvLink).apply {
                text = "Explore More"
                setTag(R.id.tvLink, articlesItem.url)
                visibility = if (articlesItem.url != null) View.VISIBLE else View.GONE
            }
            if (!articlesItem.imageUrl.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(articlesItem.imageUrl)
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
                    .into(imgNews)
            } else {
                imgNews.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_place_holder))
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem == newItem
        }
    }
}

