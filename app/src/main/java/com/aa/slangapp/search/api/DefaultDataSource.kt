package com.aa.slangapp.search.api

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

//TODO: add tests
class DefaultDataSource(private val ioDispatcher: CoroutineDispatcher) : DataSource {

    private val _showProgressBar = MutableLiveData(View.GONE)
    override val showProgressBar: LiveData<Int> = _showProgressBar

    override suspend fun fetchNewData() {
        withContext(Dispatchers.Main) {
            _showProgressBar.value = View.VISIBLE
            _showProgressBar.value = simulateNetworkDataFetch()
        }
    }

    private suspend fun simulateNetworkDataFetch(): Int = withContext(ioDispatcher) {
        delay(2000)
        View.GONE
    }

}