package com.example.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryDetailsResponse(
    @SerializedName("data") @Expose val data: CountryDetails
)