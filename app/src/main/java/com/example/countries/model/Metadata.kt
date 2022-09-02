package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentOffset") @Expose val currentOffset: Int,
    @SerializedName("totalCount") @Expose val totalCount: Int
)