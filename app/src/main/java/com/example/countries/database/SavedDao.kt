package com.example.countries.database

import androidx.room.*
import com.example.countries.model.Saved
import kotlinx.coroutines.flow.Flow

/**
 * For data stored in room database creating database access object for Insert, Query and Delete operations.
 */
@Dao
interface SavedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedCountry(saved: Saved)

    @Query("SELECT * FROM saved_table ORDER BY id ASC")
    fun readSavedCountry(): Flow<List<Saved>>

    @Delete
    suspend fun deleteSavedCountry(saved: Saved)
}