package com.example.countries.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CardviewDesignBinding
import com.example.countries.databinding.FragmentHomeBinding
import com.example.countries.model.Country
import com.example.countries.ui.viewmodel.HomeViewModel

class CountryAdapter(var mContext : Context,
                     var countryList : List<Country>,
                     var viewModel : HomeViewModel)
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
        val y = holder.binding
        y.countryItem = countries
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}