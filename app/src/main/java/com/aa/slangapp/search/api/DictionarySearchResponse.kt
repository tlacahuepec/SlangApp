package com.aa.slangapp.search.api

import com.google.gson.annotations.SerializedName

data class DictionarySearchResponse(
    @field:SerializedName("list")
    val results: List<SearchResult>
)
