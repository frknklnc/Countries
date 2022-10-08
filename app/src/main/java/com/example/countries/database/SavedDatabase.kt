package com.example.countries.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.countries.model.Saved

/**
 * Room Database
 */
@Database(entities = [Saved::class], version = 1, exportSchema = false)
@TypeConverters(CountryTypeConverter::class)
abstract class SavedDatabase : RoomDatabase() {
    abstract fun savedDao(): SavedDao
}