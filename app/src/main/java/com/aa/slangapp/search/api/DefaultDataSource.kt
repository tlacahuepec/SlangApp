package com.aa.slangapp.search.api

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.aa.slangapp.search.model.SearchResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

//TODO: add tests
class DefaultDataSource(private val ioDispatcher: CoroutineDispatcher) : DataSource {

    private val _showProgressBar = MutableLiveData(View.GONE)
    private val _showRecyclerView = MutableLiveData(View.GONE)

    override val showProgressBar: LiveData<Int> = _showProgressBar
    override val showRecyclerView: LiveData<Int> = _showRecyclerView
    override val searchResults: LiveData<List<SearchResult>> = liveData {
        val mockSearchResult = SearchResult(1, "definition", 0)
        emit(listOf(mockSearchResult))
    }

    override suspend fun fetchNewData() {
        withContext(Dispatchers.Main) {
            _showProgressBar.value = View.VISIBLE
            _showRecyclerView.value = simulateNetworkDataFetch()
            _showProgressBar.value = View.GONE
        }
    }

    private suspend fun simulateNetworkDataFetch(): Int = withContext(ioDispatcher) {
        delay(2000)
        View.VISIBLE
    }

}