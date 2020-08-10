package com.aa.slangapp.search.api

import androidx.lifecycle.LiveData
import com.aa.slangapp.search.model.SearchResult

interface DataSource {
    val searchResults: LiveData<List<SearchResult>>
    val showProgressBar: LiveData<Int>
    val showRecyclerView: LiveData<Int>
    suspend fun fetchNewData()
}