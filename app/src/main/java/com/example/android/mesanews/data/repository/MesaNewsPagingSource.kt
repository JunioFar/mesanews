package com.example.android.mesanews.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.mesanews.api.MesaNewsService
import com.example.android.mesanews.data.modal.News

private const val NEWS_STARTING_PAGE_INDEX = 1

class MesaNewsPagingSource (
        private val service: MesaNewsService,
        private val token: String
        ) : PagingSource<Int, News>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
                val page = params.key ?: NEWS_STARTING_PAGE_INDEX

                return try {
                        val response = service.getNews(page, token)
                        val news = response.newsList
                        LoadResult.Page(
                                data = news,
                                prevKey = if (page == NEWS_STARTING_PAGE_INDEX) null else page - 1,
                                nextKey = if (page == response.pagination.totalPages) null else page + 1
                        )
                } catch (exception: Exception) {
                        LoadResult.Error(exception)
                }
        }

        override fun getRefreshKey(state: PagingState<Int, News>): Int? {
                TODO("Not yet implemented")
        }

}