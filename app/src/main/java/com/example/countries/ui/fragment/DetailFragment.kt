package com.example.countries.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.toColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.countries.R
import com.example.countries.databinding.FragmentDetailBinding
import com.example.countries.model.CountryDetails
import com.example.countries.model.Saved
import com.example.countries.ui.viewmodel.DetailViewModel
import com.example.countries.ui.viewmodel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cardview_design.view.*


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var savedViewModel: SavedViewModel
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var menuItem: MenuItem

    var savedCountry = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val passCountry = args.country
        viewModel.loadCountryDetails(passCountry.code)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.detailFragment = this
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDetail)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.countryDetails.observe(viewLifecycleOwner){
            binding.countryDetailItem = viewModel.countryDetails.value

            val uri = Uri.parse(viewModel.countryDetails.value!!.flagImageUri)
            binding.imageViewFlag.loadUrl(uri)

        }

        binding.buttonLink.setOnClickListener {

            val wikiUri = Uri.parse("https://www.wikidata.org/wiki/${passCountry.wikiDataId}")
            val intent = Intent(Intent.ACTION_VIEW, wikiUri)
            startActivity(intent)
        }

        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(binding.imageViewBack).navigate(R.id.detail_to_home)
        }


        return binding.root
    }

    fun ImageView.loadUrl(url: Uri) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(600)
            .placeholder(R.drawable.flag_place_holder)
            .error(R.drawable.flag_place_holder)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
        val tempViewModel2 : SavedViewModel by viewModels()
        savedViewModel = tempViewModel2

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
        menuItem = menu!!.findItem(R.id.action_save)
        checkSavedFoods(menuItem)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save -> {
                if(!savedCountry){
                    saveToSaved(item)
                }else if(savedCountry){
                    removeFromFavorites(item)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSavedFoods(menuItem: MenuItem) {
        savedViewModel.readSavedCountry.observe(viewLifecycleOwner, Observer {
            try {
                savedCountry = false
                for (saved in it) {
                    if (saved.country.code == args.country.code) {
                        changeMenuItemColor(menuItem, R.color.red)
                        savedCountry = true
                        break
                    }else{
                        changeMenuItemColor(menuItem, R.color.white)
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsFragment", e.message.toString())
            }
        })
    }

    private fun saveToSaved(item: MenuItem) {
        val saved = Saved(args.country.code, args.country)
        savedViewModel.insertSavedCountry(saved)
        changeMenuItemColor(item, R.color.red)
        Toast.makeText(requireContext(),"${saved.country.name} add", Toast.LENGTH_SHORT).show()
        savedCountry = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        val removed = Saved(args.country.code, args.country)
        savedViewModel.deleteSavedCountry(removed)
        changeMenuItemColor(item,R.color.white)
        Toast.makeText(requireContext(),"${removed.country.name} deleted", Toast.LENGTH_SHORT).show()
        savedCountry = false
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(requireContext(), color))
    }


}