package com.example.countries.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.countries.model.Country
import com.example.countries.model.CountryResponse
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository(var cdao: CountryDao) {
    var countryList: MutableLiveData<List<Country>>

    init {
        cdao = ApiUtils.getCountryDao()
        countryList = MutableLiveData()
    }

    fun getAllCountries() : MutableLiveData<List<Country>> {
        return countryList

    }

    fun requestCountries(){



        cdao.allCountries(host = "wft-geo-db.p.rapidapi.com", key = "da44267f14mshe955f03dff1b132p1d57adjsn1fd30e84d39a").enqueue(object: Callback<CountryResponse> {
            override fun onResponse(call: Call<CountryResponse>?, response: Response<CountryResponse>) {
                Log.e("Country","${response.code()}")
                Log.e("Country","${response.body()}")
                Log.e("Country","${response.isSuccessful}")
                Log.e("Country","${response.headers()}")
                Log.e("Country","${response.message()}")
                Log.e("Country","${response}")
                val list = response.body()!!.data
                countryList.value = list
            }

            override fun onFailure(call: Call<CountryResponse>?, t: Throwable?) {}
        })

    }
}