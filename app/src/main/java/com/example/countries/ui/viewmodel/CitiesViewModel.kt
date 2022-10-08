package com.example.countries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.cities.Cities
import com.example.countries.repository.CountryDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(var drepo: CountryDetailRepository) : ViewModel() {
    var citiesList = MutableLiveData<List<Cities>>()

    init {
        citiesList = drepo.getAllCities()
    }

    fun loadCities(countryCode: String) {
        drepo.requestCities(countryCode)

    }
}