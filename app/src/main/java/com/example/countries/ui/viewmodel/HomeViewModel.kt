package com.example.countries.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country
import com.example.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var crepo: CountryRepository) : ViewModel() {

    var countryList = MutableLiveData<List<Country>>()

    init {
        countryList = crepo.getAllCountries()
    }

    fun loadCountries(){
        crepo.requestCountries()
    }


}