package com.aa.slangapp.search.data

import androidx.lifecycle.LiveData
import com.aa.dictionary.SearchResult

interface DataSource {
    val searchResults: LiveData<List<SearchResult>>
    val showProgressBar: LiveData<Int>
    val showRecyclerView: LiveData<Int>
    suspend fun fetchNewData()
}