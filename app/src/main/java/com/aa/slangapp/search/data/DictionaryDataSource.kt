package com.aa.slangapp.search.data

import com.aa.slangapp.search.api.DictionaryService
import javax.inject.Inject

class DictionaryDataSource @Inject constructor(
    private val service: DictionaryService
) : BaseDataSource() {

    suspend fun fetchData(search: String) = getResult {
        service.define(search)
    }


}