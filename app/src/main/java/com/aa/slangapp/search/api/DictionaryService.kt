package com.aa.slangapp.search.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryService {
    @GET("define")
    suspend fun define(
        @Query("term") term: String
    ): Response<DictionarySearchResponse>

    companion object {
        const val HOST = "mashape-community-urban-dictionary.p.rapidapi.com"
        const val BASE_URL = "https://$HOST/"
    }

}