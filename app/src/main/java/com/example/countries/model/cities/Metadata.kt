package com.example.countries.model.cities

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentOffset") val currentOffset: Int,
    @SerializedName("totalCount") val totalCount: Int
)