package com.aa.slangapp.search.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// TODO: add headers
interface DictionaryService {
    @GET("define")
    suspend fun define(
        @Query("term") term: String
    ): Response<DictionarySearchResponse>

    companion object {
        private const val HOST = "mashape-community-urban-dictionary.p.rapidapi.com"
        const val BASE_URL = "https://$HOST/"

//        fun create(): DictionaryService {
//            val logger = HttpLoggingInterceptor().apply { level = BASIC }
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logger)
//                .build()
//
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(DictionaryService::class.java)
//        }
    }

}