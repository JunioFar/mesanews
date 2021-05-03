package com.example.android.mesanews.data.modal

import com.google.gson.annotations.SerializedName

data class Pagination(
        @field:SerializedName("current_page") val currentPage: Int,
        @field:SerializedName("per_page") val perPage: Int,
        @field:SerializedName("total_pages") val totalPages: Int,
        @field:SerializedName("total_items") val totalItems: Int
)
