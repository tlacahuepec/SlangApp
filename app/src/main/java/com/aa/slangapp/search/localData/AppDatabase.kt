package com.aa.slangapp.search.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aa.slangapp.search.api.SearchResult
import com.aa.slangapp.search.data.DictionaryDao

@Database(
    entities = [SearchResult::class],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new table
                database.execSQL(
                    "CREATE TABLE searchResults_new (id INTEGER NOT NULL, definition TEXT NOT NULL," +
                            " thumbsUp INTEGER NOT NULL,"
                            + "word TEXT NOT NULL, thumbsDown INTEGER NOT NULL, PRIMARY KEY(id))"
                )
                // Copy the data
                database.execSQL(
                    "INSERT INTO searchResults_new (id, definition, thumbsUp, word, thumbsDown) "
                            + "SELECT id, definition, thumbsUp, word, 0 "
                            + "FROM searchResults"
                )
                // Remove the old table
                database.execSQL("DROP TABLE searchResults")
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE searchResults_new RENAME TO searchResults")
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "dictionary-db")
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }

}