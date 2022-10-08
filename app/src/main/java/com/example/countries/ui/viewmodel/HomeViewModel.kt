package com.example.countries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country
import com.example.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var crepo: CountryRepository) : ViewModel() {

    var countryList = MutableLiveData<List<Country>>()

    init {
        countryList = crepo.getAllCountries()
    }

    fun loadCountries() {
        crepo.requestCountries()
    }

    fun searchCountries(searchWords: String) {
        if (searchWords.length == 0) {
            crepo.requestCountries()
        }
        countryList.value = countryList.value!!.filter { country ->
            country.name.lowercase().contains(searchWords.lowercase())
        }
    }


}