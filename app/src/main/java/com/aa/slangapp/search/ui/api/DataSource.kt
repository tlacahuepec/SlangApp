package com.aa.slangapp.search.ui.api

import androidx.lifecycle.LiveData

interface DataSource {
    val showProgressBar: LiveData<Int>
    suspend fun fetchNewData()
}