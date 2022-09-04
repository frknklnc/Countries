package com.example.countries.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.countries.database.SavedDao
import com.example.countries.model.Country
import com.example.countries.model.CountryResponse
import com.example.countries.model.Saved
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository(var cdao: CountryDao, var savedDao: SavedDao) {
    var countryList: MutableLiveData<List<Country>>

    init {
        cdao = ApiUtils.getCountryDao()
        countryList = MutableLiveData()
    }

    fun getAllCountries() : MutableLiveData<List<Country>> {
        return countryList

    }
    //15cf55e1dbmsha0cb0a86c50005dp1e1910jsn24c033dcb189 , da44267f14mshe955f03dff1b132p1d57adjsn1fd30e84d39a , 1c633c555amshe8cf43613ffe525p1f47d8jsn348fae13fdab
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