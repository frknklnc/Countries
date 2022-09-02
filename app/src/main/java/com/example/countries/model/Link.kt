package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href") @Expose val href: String,
    @SerializedName("rel") @Expose val rel: String
)