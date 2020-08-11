package com.aa.slangapp.search.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "searchResults")
data class SearchResult(
    @PrimaryKey
    @field:SerializedName("defid")
    val id: Int,
    @field:SerializedName("definition")
    val definition: String,
    @field:SerializedName("thumbsUp")
    val thumbsUp: Int,
    @field:SerializedName("word")
    val word: String
)
