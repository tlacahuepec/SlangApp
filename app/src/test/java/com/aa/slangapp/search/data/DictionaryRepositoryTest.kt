package com.aa.slangapp.search.data

import com.aa.slangapp.search.api.DictionaryService
import com.aa.slangapp.search.localData.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class DictionaryRepositoryTest {
    private lateinit var dictionaryRepository: DictionaryRepository

    private val dictionaryDao = mock(DictionaryDao::class.java)
    private val dictionaryService = mock(DictionaryService::class.java)
    private val dictionaryDataSource = DictionaryDataSource(dictionaryService)

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.dictionaryDao()).thenReturn(dictionaryDao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        dictionaryRepository = DictionaryRepository(dictionaryDao, dictionaryDataSource)
    }

    @Test
    fun shouldLoadDataFromNetwork() {
        runBlocking {
            dictionaryRepository.searchResultsByTerm("sporty")

            Mockito.verify(dictionaryDao, Mockito.never()).getResults("sporty")
            Mockito.verifyZeroInteractions(dictionaryDao)
        }
    }

}
