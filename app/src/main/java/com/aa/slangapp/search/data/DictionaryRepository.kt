package com.aa.slangapp.search.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepository @Inject constructor(
    private val dao: DictionaryDao,
    private val remoteSource: DictionaryDataSource
) {

    fun searchResultsByTerm(term: String) = resultLiveData(
        databaseQuery = { dao.getResults(term) },
        networkCall = { remoteSource.fetchData(term) },
        saveCallResult = { dao.insertAll(it.results) })

}