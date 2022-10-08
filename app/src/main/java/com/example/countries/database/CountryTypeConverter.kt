package com.example.countries.database

import androidx.room.TypeConverter
import com.example.countries.model.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * This class converts the data we get from the API address to be able to store it in the Room database.
 */
class CountryTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun resultToString(country: Country): String {
        return gson.toJson(country)
    }

    @TypeConverter
    fun stringToResult(data: String): Country {
        val listType = object : TypeToken<Country>() {}.type
        return gson.fromJson(data, listType)
    }
}