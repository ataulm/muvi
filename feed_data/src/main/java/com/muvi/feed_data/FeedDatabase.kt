package com.muvi.feed_data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muvi.base_data.DbFilmSummary

private const val DATABASE_NAME = "feed_database"

@Database(
        version = 1,
        entities = [
            DbFilmSummary::class
        ],
        exportSchema = false
)
internal abstract class FeedDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao

    companion object {
        fun createFeedDao(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                FeedDatabase::class.java,
                DATABASE_NAME
        ).build().feedDao()
    }
}

@Dao
internal interface FeedDao {

    @Query("SELECT * FROM film_summary")
    fun getFilms(): List<DbFilmSummary>

    @Insert
    fun insertAll(films: List<DbFilmSummary>)
}
