package com.example.countries.di


import android.content.Context
import androidx.room.Room
import com.example.countries.database.SavedDao
import com.example.countries.database.SavedDatabase
import com.example.countries.repository.CountryDetailRepository
import com.example.countries.repository.CountryRepository
import com.example.countries.retrofit.ApiUtils
import com.example.countries.retrofit.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Provides
    @Singleton
    fun provideCountryDaoRepository(cdao:CountryDao,savedDao: SavedDao) : CountryRepository{
        return CountryRepository(cdao,savedDao)
    }

    @Provides
    @Singleton
    fun provideCountryDao(): CountryDao{
        return ApiUtils.getCountryDao()
    }

    @Provides
    @Singleton
    fun provideCountryDetailDaoRepository(cdao:CountryDao) : CountryDetailRepository {
        return CountryDetailRepository(cdao)
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SavedDatabase::class.java, "saved_table").build()

    @Singleton
    @Provides
    fun provideDao(database: SavedDatabase) = database.savedDao()








}