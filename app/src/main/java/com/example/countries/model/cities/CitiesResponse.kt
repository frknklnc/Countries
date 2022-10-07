package com.example.countries.model.cities

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("data") val data: List<Cities>,
    @SerializedName("links") val links: List<Link>,
    @SerializedName("metadata") val metadata: Metadata
)