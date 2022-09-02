package com.example.countries.retrofit

import com.example.countries.model.CountryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CountryDao {

    @GET("v1/geo/countries?limit=10&currencyCode=EUR")
    fun allCountries(
        @Header("X-RapidAPI-Host") host:String,
        @Header("X-RapidAPI-Key") key:String
    ) : Call<CountryResponse>

}