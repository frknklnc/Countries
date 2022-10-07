package com.example.countries.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.countries.R
import com.example.countries.databinding.FragmentCitiesBinding
import com.example.countries.ui.adapter.CitiesAdapter
import com.example.countries.ui.adapter.CountryAdapter
import com.example.countries.ui.viewmodel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : Fragment() {
    private lateinit var binding: FragmentCitiesBinding
    private val viewModel : CitiesViewModel by viewModels()
    private val args by navArgs<CitiesFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false)
        binding.citiesFragment = this

        val passCountry = args.cities

        passCountry?.code?.let { viewModel.loadCities(it) }


        viewModel.citiesList.observe(viewLifecycleOwner){
            val adapter = CitiesAdapter(requireContext(),it,viewModel)
            binding.citiesAdapter = adapter
        }


        return binding.root
    }

}