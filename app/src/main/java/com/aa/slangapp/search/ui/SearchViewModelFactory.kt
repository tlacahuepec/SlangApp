package com.aa.slangapp.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aa.slangapp.search.ui.api.DefaultDataSource
import kotlinx.coroutines.Dispatchers

object SearchViewModelFactory : ViewModelProvider.Factory {

    private val dataSource =
        DefaultDataSource(Dispatchers.IO)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(dataSource) as T
    }

}