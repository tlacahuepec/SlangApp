package com.aa.slangapp.search.data

import com.aa.dictionary.DictionaryService

class DictionaryDataSource(
    private val service: DictionaryService
) : BaseDataSource() {

    suspend fun fetchData(search: String) = getResult {
        service.define(search)
    }


}