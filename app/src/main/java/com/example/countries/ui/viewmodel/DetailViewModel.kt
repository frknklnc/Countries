package com.example.countries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.CountryDetails
import com.example.countries.repository.CountryDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var drepo: CountryDetailRepository) : ViewModel() {

    var countryDetails = MutableLiveData<CountryDetails>()

    init {
        countryDetails = drepo.getAllCountryDetails()
    }

    fun loadCountryDetails(countryCode: String) {
        drepo.requestCountryDetails(countryCode)
    }
}