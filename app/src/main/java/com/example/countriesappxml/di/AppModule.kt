package com.example.countriesappxml.di

import com.example.countriesappxml.common.Constants
import com.example.countriesappxml.data.remote.CountryApi
import com.example.countriesappxml.data.repository.CountryRepositoryImpl
import com.example.countriesappxml.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryApi() :CountryApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(api : CountryApi) : CountryRepository{
        return CountryRepositoryImpl(api)
    }
}