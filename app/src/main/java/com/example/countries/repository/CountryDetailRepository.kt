package com.example.countries.repository

import androidx.lifecycle.MutableLiveData
import com.example.countries.BuildConfig
import com.example.countries.model.CountryDetails
import com.example.countries.model.CountryDetailsResponse
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailRepository(var countryDao: CountryDao) {
    var apiKey = BuildConfig.API_KEY
    var countryDetails: MutableLiveData<CountryDetails>

    init {
        countryDao = ApiUtils.getCountryDao()
        countryDetails = MutableLiveData()
    }

    fun getAllCountryDetails() : MutableLiveData<CountryDetails> {
        return countryDetails
    }

    //With this function, I get the data from our API address.
    fun requestCountryDetails(countryCode:String){
        countryDao.countryDetails(host = "wft-geo-db.p.rapidapi.com", key = apiKey,code = countryCode)
            .enqueue(object: Callback<CountryDetailsResponse> {
            override fun onResponse(call: Call<CountryDetailsResponse>?, response: Response<CountryDetailsResponse>) {
                val list = response.body()!!.data
                countryDetails.value = list
            }

            override fun onFailure(call: Call<CountryDetailsResponse>?, t: Throwable?) {}
        })

    }
}

