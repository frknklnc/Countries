package com.example.countries.model.cities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cities(
    @SerializedName("countryCode") @Expose val code: String,
    @SerializedName("fipsCode") @Expose val fipsCode: String,
    @SerializedName("isoCode") @Expose val isoCode: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("wikiDataId") @Expose val wikiDataId: String
)