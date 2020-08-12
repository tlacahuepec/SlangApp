package com.aa.slangapp.search.api


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class DictionaryServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dictionaryService: DictionaryService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        dictionaryService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun shouldFormCorrectRequestPath() {
        runBlocking {
            enqueueResponse("sportyDefinitions.json")
            val response = dictionaryService.define(DEFAULT_SEARCH).body()

            val request = mockWebServer.takeRequest()

            Assert.assertNotNull(response)
            assertThat(request.path, CoreMatchers.`is`("/define?term=sporty"))
        }
    }

    @Test
    fun shouldReturnCorrectResponse() {
        runBlocking {
            enqueueResponse("sportyDefinitions.json")
            val response = dictionaryService.define(DEFAULT_SEARCH).body()

            Assert.assertNotNull(response)

            val dictionarySearchResponse = response!!.results
            assertThat(dictionarySearchResponse.size, `is`(2))
            val searchResponse = dictionarySearchResponse.first()
            assertThat(
                searchResponse.definition,
                `is`("Wearing causal in a [very cool] and yet [stylish] [fashion] way!")
            )
            assertThat(searchResponse.id, `is`(10256511))
            assertThat(searchResponse.thumbsDown, `is`(5))
            assertThat(searchResponse.thumbsUp, `is`(210))
            assertThat(searchResponse.word, `is`("sporty"))


        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("api-response/$fileName")
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        if (source != null) {
            mockWebServer.enqueue(
                mockResponse.setBody(
                    source.readString(Charsets.UTF_8)
                )
            )
        }
    }

    companion object {
        const val DEFAULT_SEARCH = "sporty"
    }
}