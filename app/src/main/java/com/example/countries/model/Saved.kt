package com.example.countries.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_table")
data class Saved(@PrimaryKey
                 val id: String,
                 val country: Country)