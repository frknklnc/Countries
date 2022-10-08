package com.example.countries.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CardviewDesignCitiesBinding
import com.example.countries.model.cities.Cities
import com.example.countries.ui.viewmodel.CitiesViewModel


class CitiesAdapter(
    var mContext: Context,
    var citiesList: List<Cities>,
    var viewModel: CitiesViewModel
) : RecyclerView.Adapter<CitiesAdapter.CitiesCardHolder>() {

    inner class CitiesCardHolder(binding: CardviewDesignCitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: CardviewDesignCitiesBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: CardviewDesignCitiesBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.cardview_design_cities, parent, false
        )
        return CitiesCardHolder(binding)

    }

    override fun onBindViewHolder(holder: CitiesCardHolder, position: Int) {
        val cities = citiesList.get(position)
        val c = holder.binding
        Log.e("asde", cities.toString())
        c.citiesItem = cities
    }


    override fun getItemCount(): Int {
        return citiesList.size
    }


}