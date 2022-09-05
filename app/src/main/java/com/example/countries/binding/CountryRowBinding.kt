package com.example.countries.binding

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.countries.R

class CountryRowBinding {
    companion object{
        @JvmStatic
        @BindingAdapter("app:saved_country")
        fun savedCountry(imageView: ImageView, saved: Boolean){
            if(saved){
                imageView.setColorFilter(
                    ContextCompat.getColor(imageView.context, R.color.black1)
                )
            }else{
                imageView.setColorFilter(
                    ContextCompat.getColor(imageView.context, R.color.iconColor)
                )
            }
        }
    }
}