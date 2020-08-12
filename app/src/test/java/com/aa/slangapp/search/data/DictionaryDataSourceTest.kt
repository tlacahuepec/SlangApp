package com.aa.slangapp.search.data

import com.aa.slangapp.search.api.DictionarySearchResponse
import com.aa.slangapp.search.api.DictionaryService
import com.aa.slangapp.search.api.SearchResult
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

@RunWith(JUnit4::class)
class DictionaryDataSourceTest {

    private lateinit var dictionaryDataSource: DictionaryDataSource

    private val dictionaryService = mock(DictionaryService::class.java)

    @Before
    fun init() {
        dictionaryDataSource = DictionaryDataSource(dictionaryService)
    }

    @Test
    fun shouldReturnErrorWhenServiceFails() {
        runBlocking {
            val mockResponse =
                Response.error<DictionarySearchResponse>(404, mock(ResponseBody::class.java))
            `when`(dictionaryService.define("sporty")).thenReturn(mockResponse)
            val response = dictionaryDataSource.fetchData("sporty")
            assertThat(
                response.message,
                `is`("Network call has failed for a following reason:  404 Response.error()")
            )
            assertThat(response.status, `is`(Result.Status.ERROR))
        }
    }

    @Test
    fun shouldReturnSuccessWhenServiceWorks() {
        runBlocking {
            val dictionarySearchResponse =
                DictionarySearchResponse(listOf(SearchResult(1, "", 0, "", 2)))
            val mockResponse =
                Response.success(dictionarySearchResponse)
            `when`(dictionaryService.define("sporty")).thenReturn(mockResponse)
            val response = dictionaryDataSource.fetchData("sporty")
            assertNull(response.message)
            assertThat(response.status, `is`(Result.Status.SUCCESS))
        }
    }

}
