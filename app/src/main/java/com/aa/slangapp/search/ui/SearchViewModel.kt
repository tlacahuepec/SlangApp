package com.aa.slangapp.search.ui

import androidx.lifecycle.ViewModel
import com.aa.slangapp.search.data.DictionaryRepository
import javax.inject.Inject

//TODO: add tests
//class SearchViewModel(private val dataSource: DataSource) : ViewModel() {
//    init {
//        Log.i("SearchViewModel", "SearchViewModel created...")
//    }
//
//    val searchResults = dataSource.searchResults
//    val showRecyclerView = dataSource.showRecyclerView
//
//    val showProgressBarValue = dataSource.showProgressBar
//
//    fun onSearch() {
//        viewModelScope.launch {
//            dataSource.fetchNewData()
//        }
//    }
//}

class SearchViewModel @Inject constructor(dictionaryRepository: DictionaryRepository) :
    ViewModel() {
    val searchResults = dictionaryRepository.searchResults

    fun onSearch() {
    }
}