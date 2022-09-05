package com.example.countries.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CardviewDesignSavedBinding
import com.example.countries.model.Country
import com.example.countries.model.Saved
import com.example.countries.ui.fragment.HomeFragmentDirections
import com.example.countries.ui.fragment.SavedFragmentDirections
import com.example.countries.ui.viewmodel.SavedViewModel
import com.example.countries.utils.showToast
import kotlinx.android.synthetic.main.cardview_design_saved.view.*

class SavedAdapter(var mContext : Context,
                   var countryList : List<Saved>,
                   var viewModel : SavedViewModel
)
    : RecyclerView.Adapter<SavedAdapter.SavedCardHolder>(){

    inner class SavedCardHolder(binding: CardviewDesignSavedBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding : CardviewDesignSavedBinding
        init {
            this.binding = binding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: CardviewDesignSavedBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.cardview_design_saved,parent,false)

        return SavedCardHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedCardHolder, position: Int) {
        val savedCountries = countryList.get(position)

        val saved = holder.binding
        saved.savedItem = savedCountries

        saved.cardViewSaved.imageViewSaved.setOnClickListener {
            removeFromSaved(savedCountries)
        }

        saved.cardViewSaved.setOnClickListener {
            val pass = SavedFragmentDirections.savedToDetail(country = savedCountries.country)
            Navigation.findNavController(it).navigate(pass)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    //This function deletes the country from the room saved repository.
    private fun removeFromSaved(saved: Saved) {
        viewModel.deleteSavedCountry(saved)
        showToast(mContext,"${saved.country.name} deleted.")
    }



}