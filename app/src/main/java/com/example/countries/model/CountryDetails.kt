package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This is the country details data class.
 * @param callingCode
 * @param capital
 * @param code
 * @param currencyCodes
 * @param flagImageUri
 * @param name
 * @param numRegions
 * @param wikiDataId
 */
data class CountryDetails(
    @SerializedName("callingCode") @Expose val callingCode: String,
    @SerializedName("capital") @Expose val capital: String,
    @SerializedName("code") @Expose val code: String,
    @SerializedName("currencyCodes") @Expose val currencyCodes: List<String>,
    @SerializedName("flagImageUri") @Expose val flagImageUri: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("numRegions") @Expose val numRegions: Int,
    @SerializedName("wikiDataId") @Expose val wikiDataId: String
)