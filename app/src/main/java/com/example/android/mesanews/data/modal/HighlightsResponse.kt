package com.example.android.mesanews.data.modal

import com.google.gson.annotations.SerializedName

data class HighlightsResponse(
    @field:SerializedName("data") val highlightList: List<News>?)
