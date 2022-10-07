package com.example.countries.model.cities

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href") val href: String,
    @SerializedName("rel") val rel: String
)