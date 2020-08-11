package com.aa.slangapp.search.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepository @Inject constructor(
    private val dao: DictionaryDao,
    private val remoteSource: DictionaryDataSource
) {

    private val search = "Sporty"

    val searchResults = resultLiveData(
        databaseQuery = { dao.getResults(search) },
        networkCall = { remoteSource.fetchData(search) },
        saveCallResult = { dao.insertAll(it.results) })

}