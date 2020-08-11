package com.aa.slangapp.search.data

class DictionaryRepository(
    private val dao: DictionaryDao,
    private val remoteSource: DictionaryDataSource,
    private val search: String
) {

    val searchResults = resultLiveData(
        databaseQuery = { dao.getResults(search) },
        networkCall = { remoteSource.fetchData(search) },
        saveCallResult = { dao.insertAll(it.results) })

}