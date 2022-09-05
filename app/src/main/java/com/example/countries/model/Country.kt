package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * This is the country data class.
 * @param code
 * @param currencyCodes
 * @param name
 * @param wikiDataId
 */
data class Country(
    @SerializedName("code") @Expose val code: String,
    @SerializedName("currencyCodes") @Expose val currencyCodes: List<String>,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("wikiDataId") @Expose val wikiDataId: String
) : Serializable{}