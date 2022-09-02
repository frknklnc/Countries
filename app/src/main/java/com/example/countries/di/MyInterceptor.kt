package com.example.countries.di

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Host","wft-geo-db.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key","da44267f14mshe955f03dff1b132p1d57adjsn1fd30e84d39a")
            .build()
        return chain.proceed(request)
    }
}