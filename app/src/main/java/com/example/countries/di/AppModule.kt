package com.example.countries.di


import com.example.countries.repository.CountryRepository
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Provides
    @Singleton
    fun provideCountryDaoRepository(cdao:CountryDao) : CountryRepository{
        return CountryRepository(cdao)
    }

    @Provides
    @Singleton
    fun provideCountryDao(): CountryDao{
        return ApiUtils.getCountryDao()
    }



}