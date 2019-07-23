package com.muvi.feed_cache

import androidx.room.*
import com.muvi.base_data.DbFilmSummary

@Database(
        version = 1,
        entities = [
            DbFilmSummary::class
        ],
        exportSchema = false
)
internal abstract class FeedDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao
}

@Dao
internal interface FeedDao {

    @Query("SELECT * FROM film_summary")
    suspend fun getFilms(): List<DbFilmSummary>

    @Insert
    suspend fun insertAll(films: List<DbFilmSummary>)
}
