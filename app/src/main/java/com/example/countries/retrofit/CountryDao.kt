package com.example.countries.retrofit

import com.example.countries.model.CountryDetailsResponse
import com.example.countries.model.CountryResponse
import retrofit2.Call
import retrofit2.http.*

interface CountryDao {

    @GET("v1/geo/countries?limit=10&currencyCode=EUR")
    fun allCountries(
        @Header("X-RapidAPI-Host") host:String,
        @Header("X-RapidAPI-Key") key:String
    ) : Call<CountryResponse>

    @GET("v1/geo/countries/{code}")
    fun countryDetails(
        @Header("X-RapidAPI-Host") host:String,
        @Header("X-RapidAPI-Key") key:String,
        @Path("code") code:String
    ): Call<CountryDetailsResponse>

}