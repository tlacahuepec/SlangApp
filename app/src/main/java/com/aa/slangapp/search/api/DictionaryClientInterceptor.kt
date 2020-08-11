package com.aa.slangapp.com.aa.slangapp.search.api

import com.aa.slangapp.search.api.DictionaryService.Companion.HOST
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class DictionaryClientInterceptor(private val key: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", HOST)
            .addHeader("x-rapidapi-key", key)
            .build()
        return chain.proceed(request)
    }
}