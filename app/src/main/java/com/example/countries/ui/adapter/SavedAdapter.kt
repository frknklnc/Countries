package com.example.countries.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CardviewDesignSavedBinding
import com.example.countries.model.Country
import com.example.countries.model.Saved
import com.example.countries.ui.viewmodel.SavedViewModel
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
        val s = holder.binding
        s.savedItem = savedCountries

        s.cardViewSaved.imageViewSaved.setOnClickListener {
            removeFromSaved(savedCountries)
        }

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    private fun removeFromSaved(saved: Saved) {
        viewModel.deleteSavedCountry(saved)
        Log.e("Saved","silindi")
        //changeColorSavedIcon(binding.ivDetailFavorite, R.color.mediumGray)
        Toast.makeText(mContext,"${saved.country.name} deleted", Toast.LENGTH_SHORT).show()
        //Snackbar.make(mContext,"Country deleted.", Snackbar.LENGTH_SHORT).show()
        //savedCountry = false
    }



}