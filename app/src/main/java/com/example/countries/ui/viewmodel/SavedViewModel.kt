package com.example.countries.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.countries.model.Saved
import com.example.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(var countryRepo: CountryRepository): ViewModel() {
    var readSavedCountry: LiveData<List<Saved>> = countryRepo.readSavedCountry().asLiveData()

    fun insertSavedCountry(saved: Saved) =
        viewModelScope.launch(Dispatchers.IO) {
        countryRepo.inserSavedCountry(saved)
    }

    fun deleteSavedCountry(saved: Saved) =
        viewModelScope.launch(Dispatchers.IO){
        countryRepo.deleteSavedCountry(saved)
    }
}