package com.example.countries.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.countries.R
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.model.Saved
import com.example.countries.ui.adapter.CountryAdapter
import com.example.countries.ui.viewmodel.HomeViewModel
import com.example.countries.ui.viewmodel.SavedViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var savedViewModel: SavedViewModel
    private var savedList: List<Saved> = emptyList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeFragment = this

        //toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.loadCountries()

        savedViewModel.readSavedCountry.observe(viewLifecycleOwner){
            savedList = it
        }
        viewModel.countryList.observe(viewLifecycleOwner){
            val adapter = CountryAdapter(requireContext(),it,savedList,viewModel,savedViewModel)
            binding.countryAdapter = adapter
        }

        return binding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setHasOptionsMenu(true)
        val tempViewModel : HomeViewModel by viewModels()
        val tempViewModel2 : SavedViewModel by viewModels()
        viewModel = tempViewModel
        savedViewModel = tempViewModel2

    }


}