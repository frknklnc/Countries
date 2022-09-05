package com.example.countries.retrofit

/**
 * Binding the interface with the Retrofit object, this method will be run every time we want to use the methods in the Interface.
 */
class ApiUtils {
    companion object{
        val BASE_URL = "https://wft-geo-db.p.rapidapi.com/"

        fun getCountryDao(): CountryDao{
            return RetrofitClient.getClient(BASE_URL).create(CountryDao::class.java)
        }
    }
}