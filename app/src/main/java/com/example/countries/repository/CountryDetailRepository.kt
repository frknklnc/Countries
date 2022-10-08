package com.example.countries.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.countries.BuildConfig
import com.example.countries.model.CountryDetails
import com.example.countries.model.CountryDetailsResponse
import com.example.countries.model.cities.Cities
import com.example.countries.model.cities.CitiesResponse
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailRepository(var countryDao: CountryDao) {
    var apiKey = BuildConfig.API_KEY
    var countryDetails: MutableLiveData<CountryDetails>
    var cities: MutableLiveData<List<Cities>>

    init {
        countryDao = ApiUtils.getCountryDao()
        countryDetails = MutableLiveData()
        cities = MutableLiveData()
    }

    fun getAllCountryDetails(): MutableLiveData<CountryDetails> {
        return countryDetails
    }

    fun getAllCities(): MutableLiveData<List<Cities>> {
        return cities
    }

    //With this function, I get the data from our API address.
    fun requestCountryDetails(countryCode: String) {
        countryDao.countryDetails(
            host = "wft-geo-db.p.rapidapi.com",
            key = apiKey,
            code = countryCode
        )
            .enqueue(object : Callback<CountryDetailsResponse> {
                override fun onResponse(
                    call: Call<CountryDetailsResponse>?,
                    response: Response<CountryDetailsResponse>
                ) {
                    val list = response.body()!!.data
                    countryDetails.value = list
                }

                override fun onFailure(call: Call<CountryDetailsResponse>?, t: Throwable?) {}
            })

    }

    fun requestCities(countryCode: String) {
        countryDao.allCities(host = "wft-geo-db.p.rapidapi.com", key = apiKey, code = countryCode)
            .enqueue(object : Callback<CitiesResponse> {
                override fun onResponse(
                    call: Call<CitiesResponse>?,
                    response: Response<CitiesResponse>
                ) {
                    response.body()?.let {
                        val list = it.data
                        Log.e("asde", list.toString())
                        cities.value = list
                    }
                }

                override fun onFailure(call: Call<CitiesResponse>?, t: Throwable?) {
                    Log.e("asd", "hata3")
                }
            })

    }


}

