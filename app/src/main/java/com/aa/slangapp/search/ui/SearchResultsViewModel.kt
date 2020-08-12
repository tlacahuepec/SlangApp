package com.aa.slangapp.search.ui

import androidx.lifecycle.ViewModel
import com.aa.slangapp.search.data.DictionaryRepository
import javax.inject.Inject

class SearchResultsViewModel @Inject constructor(private val dictionaryRepository: DictionaryRepository) :
    ViewModel() {

    lateinit var term: String

    val searchResults by lazy { dictionaryRepository.searchResultsByTerm(term) }

}