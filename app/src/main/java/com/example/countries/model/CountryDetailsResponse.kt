package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * JSON response class for country details.
 * @param data
 */
data class CountryDetailsResponse(
    @SerializedName("data") @Expose val data: CountryDetails
)