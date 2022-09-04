package com.example.countries.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.countries.model.Country
import com.example.countries.model.CountryDetails
import com.example.countries.model.CountryDetailsResponse
import com.example.countries.model.CountryResponse
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailRepository(var cdao: CountryDao) {

    var countryDetails: MutableLiveData<CountryDetails>

    init {
        cdao = ApiUtils.getCountryDao()
        countryDetails = MutableLiveData()
    }

    fun getAllCountryDetails() : MutableLiveData<CountryDetails> {
        return countryDetails
    }

    fun requestCountryDetails(countryCode:String){
        cdao.countryDetails(host = "wft-geo-db.p.rapidapi.com", key = "da44267f14mshe955f03dff1b132p1d57adjsn1fd30e84d39a",code = countryCode)
            .enqueue(object: Callback<CountryDetailsResponse> {
            override fun onResponse(call: Call<CountryDetailsResponse>?, response: Response<CountryDetailsResponse>) {
                Log.e("Country","${response.code()}")
                Log.e("Country","${response.body()}")
                Log.e("Country","${response.isSuccessful}")
                Log.e("Country","${response.headers()}")
                Log.e("Country","${response.message()}")
                Log.e("Country","${response}")
                val list = response.body()!!.data
                countryDetails.value = list
            }

            override fun onFailure(call: Call<CountryDetailsResponse>?, t: Throwable?) {}
        })

    }
}

