package com.example.android.mesanews.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mesanews.R
import com.example.android.mesanews.data.modal.News
import com.example.android.mesanews.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter : PagingDataAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
                NewsItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news)
        }
    }


    class NewsViewHolder(
            private val binding: NewsItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: News) {
            binding.news = item
            Picasso.get().load(item.imageUrl).placeholder(R.drawable.sample).into(binding.imageView4)
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}