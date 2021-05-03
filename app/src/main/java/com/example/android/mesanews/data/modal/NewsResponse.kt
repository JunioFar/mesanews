package com.example.android.mesanews.data.modal

import com.google.gson.annotations.SerializedName

data class NewsResponse(
        @field:SerializedName("pagination") val pagination: Pagination,
        @field:SerializedName("data") val newsList: List<News>
)
