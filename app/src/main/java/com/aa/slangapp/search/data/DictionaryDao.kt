package com.aa.slangapp.search.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aa.dictionary.SearchResult

@Dao
interface DictionaryDao {

    @Query(
        "SELECT * FROM searchResults WHERE word LIKE :search ORDER BY thumbsUp DESC"
    )
    fun getResults(search: String): LiveData<List<SearchResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<SearchResult>)
}
