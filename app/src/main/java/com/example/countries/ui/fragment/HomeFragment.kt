package com.example.countries.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.countries.R
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.ui.adapter.CountryAdapter
import com.example.countries.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeFragment = this



        viewModel.loadCountries()
        Log.e("Country","${viewModel.countryList}")
        viewModel.countryList.observe(viewLifecycleOwner){
            val adapter = CountryAdapter(requireContext(),it,viewModel)
            binding.countryAdapter = adapter
        }



        return binding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel

    }

}