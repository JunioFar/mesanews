package com.example.android.mesanews.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mesanews.R
import com.example.android.mesanews.data.modal.News
import com.example.android.mesanews.databinding.HighlightItemBinding
import com.squareup.picasso.Picasso

class HighlightsAdapter : ListAdapter<News, HighlightsAdapter.HighlightViewHolder>(HighlightDiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        return HighlightViewHolder(
            HighlightItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {

        if (currentList.isNotEmpty()) {
            val highlight = getItem(position)
            if (highlight != null) {
                holder.bind(highlight)
            }
        }
    }


    class HighlightViewHolder(
        private val binding: HighlightItemBinding
    ) : RecyclerView.ViewHolder(binding.root){


        fun bind(item: News) {
            binding.news = item
            Picasso.get().load(item.imageUrl).placeholder(R.drawable.sample).into(binding.imageView2)
        }
    }
}

private class HighlightDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.description == newItem.description
    }
}