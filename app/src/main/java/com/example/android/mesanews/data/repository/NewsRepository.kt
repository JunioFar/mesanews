package com.example.android.mesanews.data.repository


import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.mesanews.api.MesaNewsService
import com.example.android.mesanews.data.modal.HighlightsResponse
import com.example.android.mesanews.data.modal.News
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val service: MesaNewsService) {

    suspend fun highlightsRequest(context: Context) : Response<HighlightsResponse> {
        val token = getToken(context)
        val result = service.getHighlights(token)
        result.body()?.highlightList?.sortedBy { it.publishedAt }
        return result
    }

    fun newsRequest(context: Context) : Flow<PagingData<News>> {
        val token: String = getToken(context)
        return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = 20),
                pagingSourceFactory = { MesaNewsPagingSource(service, token)}
        ).flow
    }

    private fun getToken(context: Context): String {
        return context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).getString("TOKEN", "").toString()
    }
}