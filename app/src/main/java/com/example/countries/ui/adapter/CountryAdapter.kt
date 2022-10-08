package com.example.countries.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.databinding.CardviewDesignBinding
import com.example.countries.model.Country
import com.example.countries.model.Saved
import com.example.countries.ui.fragment.HomeFragmentDirections
import com.example.countries.ui.viewmodel.HomeViewModel
import com.example.countries.ui.viewmodel.SavedViewModel
import com.example.countries.utils.showToast
import kotlinx.android.synthetic.main.cardview_design.view.*

class CountryAdapter(
    var mContext: Context,
    var countryList: List<Country>,
    var savedList: List<Saved>,
    var viewModel: HomeViewModel,
    var savedViewModel: SavedViewModel
) : RecyclerView.Adapter<CountryAdapter.CountryCardHolder>() {


    inner class CountryCardHolder(binding: CardviewDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: CardviewDesignBinding

        init {
            binding.saved = false
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: CardviewDesignBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.cardview_design, parent, false
        )
        return CountryCardHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryCardHolder, position: Int) {
        val countries = countryList.get(position)
        val c = holder.binding
        c.countryItem = countries

        var x = checkSavedCountry(countries)

        c.saved = x

        var savedCountry = x
        c.cardView.ivHomeSaved.setOnClickListener {
            clickedSaved(countries, savedCountry)
            savedCountry = !savedCountry

            if (savedCountry) {
                c.cardView.ivHomeSaved.setColorFilter(
                    ContextCompat.getColor(
                        c.cardView.ivHomeSaved.context,
                        R.color.black1
                    )
                )
            } else {
                c.cardView.ivHomeSaved.setColorFilter(
                    ContextCompat.getColor(
                        c.cardView.ivHomeSaved.context,
                        R.color.iconColor
                    )
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

    /**
     *This function handles how the app acts when pressed at the save button.
     * If already saved it removes the country from the saved repository.
     * If not saved it adds the country to the saved repository.
     *
     * @param country
     * @param savedCountry
     *
     */

    fun clickedSaved(country: Country, savedCountry: Boolean) {
        if (!savedCountry) {
            saveToSaved(country)
        } else {
            removeFromSaved(country)
        }
    }

    //This function saves the country to the room saved repository.
    private fun saveToSaved(country: Country) {
        val saved = Saved(country.code, country)
        savedViewModel.insertSavedCountry(saved)
        showToast(mContext, "${country.name} added.")
    }

    //This function deletes the country from the room saved repository.
    private fun removeFromSaved(country: Country) {
        val saved = Saved(country.code, country)
        savedViewModel.deleteSavedCountry(saved)
        showToast(mContext, "${country.name} deleted.")

    }

    //This function checks if the country is already has been saved.
    private fun checkSavedCountry(country: Country): Boolean {
        for (saved in savedList) {
            if (saved.country.code == country.code) {
                return true
            }
        }
        return false
    }
}
