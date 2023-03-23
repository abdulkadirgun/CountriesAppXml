package com.example.countriesappxml.data.remote

import com.example.countriesappxml.data.remote.dto.CountryDto
import retrofit2.http.GET

interface CountryApi {

    @GET("v3.1/all")
    suspend fun getCountries() : List<CountryDto>
}