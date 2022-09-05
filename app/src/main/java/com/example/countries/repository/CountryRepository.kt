package com.example.countries.repository

import androidx.lifecycle.MutableLiveData
import com.example.countries.BuildConfig
import com.example.countries.database.SavedDao
import com.example.countries.model.Country
import com.example.countries.model.CountryResponse
import com.example.countries.model.Saved
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository(var countryDao: CountryDao, var savedDao: SavedDao) {
    var apiKey = BuildConfig.API_KEY
    var countryList: MutableLiveData<List<Country>>

    init {
        countryDao = ApiUtils.getCountryDao()
        countryList = MutableLiveData()
    }

    fun getAllCountries() : MutableLiveData<List<Country>> {
        return countryList

    }

    //With this function, I get the data from our API address.
    fun requestCountries(){
        countryDao.allCountries(host = "wft-geo-db.p.rapidapi.com", key = apiKey).enqueue(object: Callback<CountryResponse> {
            override fun onResponse(call: Call<CountryResponse>?, response: Response<CountryResponse>) {
                val list = response.body()!!.data
                countryList.value = list
            }

            override fun onFailure(call: Call<CountryResponse>?, t: Throwable?) {}
        })
    }

    fun readSavedCountry(): Flow<List<Saved>>{
        return savedDao.readSavedCountry()
    }
    suspend fun inserSavedCountry(saved: Saved){
        savedDao.insertSavedCountry(saved)
    }
    suspend fun deleteSavedCountry(saved: Saved){
        savedDao.deleteSavedCountry(saved)
    }


}