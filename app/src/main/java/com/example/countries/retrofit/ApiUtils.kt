package com.example.countries.retrofit

import java.util.*

class ApiUtils {
    companion object{
        // https://wft-geo-db.p.rapidapi.com/v1/geo/countries?limit=10&currencyCode=EUR
        
        val BASE_URL = "https://wft-geo-db.p.rapidapi.com/"

        fun getCountryDao(): CountryDao{
            val x = RetrofitClient.getClient(BASE_URL).create(CountryDao::class.java)

            return x
        }
    }
}