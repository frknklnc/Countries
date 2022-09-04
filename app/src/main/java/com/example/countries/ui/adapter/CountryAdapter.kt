package com.example.countries.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CardviewDesignBinding
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.model.Country
import com.example.countries.model.Saved
import com.example.countries.ui.fragment.DetailFragmentArgs
import com.example.countries.ui.fragment.HomeFragmentDirections
import com.example.countries.ui.viewmodel.HomeViewModel
import com.example.countries.ui.viewmodel.SavedViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.cardview_design.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountryAdapter(var mContext : Context,
                     var countryList : List<Country>,
                     var viewModel : HomeViewModel,
                     var savedViewModel: SavedViewModel)
    : RecyclerView.Adapter<CountryAdapter.CountryCardHolder>(){


    inner class CountryCardHolder(binding: CardviewDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding : CardviewDesignBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding:CardviewDesignBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.cardview_design,parent,false)
        return CountryCardHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryCardHolder, position: Int) {
        val countries = countryList.get(position)
        val c = holder.binding
        c.countryItem = countries

        var savedCountry = false


        c.cardView.ivHomeSaved.setOnClickListener {
            clickedSaved(countries,savedCountry)
            savedCountry = !savedCountry
            if (savedCountry){
                DrawableCompat.setTint(
                    c.cardView.ivHomeSaved.getDrawable(),
                    ContextCompat.getColor(mContext, R.color.red)
                )
            }else{
                DrawableCompat.setTint(
                    c.cardView.ivHomeSaved.getDrawable(),
                    ContextCompat.getColor(mContext, R.color.black)
                )
            }
        }
        c.cardView.setOnClickListener {
            val pass = HomeFragmentDirections.homeToDetail(country = countries)
            Navigation.findNavController(it).navigate(pass)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun clickedSaved(country: Country,savedCountry:Boolean) {
        if (!savedCountry) {
            saveToSaved(country)
        } else {
            removeFromSaved(country)
        }
    }

    private fun saveToSaved(country: Country) {
        val saved = Saved(country.code,country)
        savedViewModel.insertSavedCountry(saved)
        Toast.makeText(mContext,"${country.name} add",Toast.LENGTH_SHORT).show()
    }

    private fun removeFromSaved(country: Country) {
        val saved = Saved(country.code, country)
        savedViewModel.deleteSavedCountry(saved)
        Toast.makeText(mContext,"deleted",Toast.LENGTH_SHORT).show()

    }
    }

    /*private fun changeColorSavedIcon(imageView: ImageView, color: Int) {
        DrawableCompat.setTint(
            imageView.getDrawable(),
            ContextCompat.getColor(requireContext(), color)
        )
    }*/
