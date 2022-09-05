package com.example.countries.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This is the saved country data class.
 * @param id
 * @param country
 */
@Entity(tableName = "saved_table")
data class Saved(@PrimaryKey
                 @SerializedName("id") @Expose val id: String,
                 @SerializedName("country") @Expose val country: Country)