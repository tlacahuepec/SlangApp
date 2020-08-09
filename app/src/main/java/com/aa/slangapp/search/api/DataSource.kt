package com.aa.slangapp.search.api

import androidx.lifecycle.LiveData

interface DataSource {
    val showProgressBar: LiveData<Int>
    suspend fun fetchNewData()
}