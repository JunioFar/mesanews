package com.example.android.mesanews.data.modal

import com.google.gson.annotations.SerializedName
import java.util.*

data class News(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("content") val content: String,
    @field:SerializedName("author") val author: String,
    @field:SerializedName("published_at") val publishedAt: Date,
    @field:SerializedName("highlight") val isHighlight: Boolean,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("image_url") val imageUrl: String
)