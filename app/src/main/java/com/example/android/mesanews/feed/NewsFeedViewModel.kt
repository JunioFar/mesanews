package com.example.android.mesanews.feed

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android.mesanews.data.modal.HighlightsResponse
import com.example.android.mesanews.data.modal.News
import com.example.android.mesanews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    fun getHighlights(context: Context) : LiveData<Response<HighlightsResponse>> = liveData {
        val data = repository.highlightsRequest(context)
        data.body()?.highlightList?.sortedBy { it.title }
        emit(data)
    }

    var newsResponse: Flow<PagingData<News>>? = null

    fun getNews(context: Context) : Flow<PagingData<News>> {
        val result = repository.newsRequest(context).cachedIn(viewModelScope)
        newsResponse = result
        return result
    }
}