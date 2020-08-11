package com.aa.slangapp.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aa.slangapp.search.data.DataSource
import kotlinx.coroutines.launch

//TODO: add tests
class SearchViewModel(private val dataSource: DataSource) : ViewModel() {
    init {
        Log.i("SearchViewModel", "SearchViewModel created...")
    }

    val searchResults = dataSource.searchResults
    val showRecyclerView = dataSource.showRecyclerView

    val showProgressBarValue = dataSource.showProgressBar

    fun onSearch() {
        viewModelScope.launch {
            dataSource.fetchNewData()
        }
    }
}