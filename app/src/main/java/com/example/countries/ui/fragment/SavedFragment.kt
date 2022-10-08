package com.example.countries.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.countries.R
import com.example.countries.databinding.FragmentSavedBinding
import com.example.countries.ui.adapter.SavedAdapter
import com.example.countries.ui.viewmodel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel: SavedViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        binding.savedFragment = this

        //toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarSaved)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.readSavedCountry.observe(viewLifecycleOwner) {
            Log.e("Saved", "${viewModel.readSavedCountry}")
            val adapter = SavedAdapter(requireContext(), it, viewModel)
            binding.savedAdapter = adapter

            if (it.isNullOrEmpty()) {
                binding.tvNoCountry.visibility = View.VISIBLE
            } else {
                binding.tvNoCountry.visibility = View.GONE
            }
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SavedViewModel by viewModels()
        viewModel = tempViewModel

    }

}