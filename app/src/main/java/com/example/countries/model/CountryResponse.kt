package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * JSON response class for countries.
 * @param data
 * @param links
 * @param metadata
 */
data class CountryResponse(
    @SerializedName("data") @Expose val data: List<Country>,
    @SerializedName("links") @Expose val links: List<Link>,
    @SerializedName("metadata") @Expose val metadata: Metadata
)