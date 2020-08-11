package com.aa.slangapp.search.api


import com.aa.slangapp.com.aa.slangapp.search.api.DictionaryService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

//TODO: use mockServer
// https://medium.com/@hanru.yeh/unit-test-retrofit-and-mockwebserver-a3e4e81fd2a2
class DictionaryServiceTest {

    private lateinit var dictionaryService: DictionaryService

    @Before
    fun setup() {
        dictionaryService = DictionaryService.create()
    }

    @Test
    fun shouldGetListOfResults() = runBlocking {
        // call the api
        val response = dictionaryService.define("Sporty")
        // verify the response is OK
        //assertFalse(response.list.isEmpty())
    }


}